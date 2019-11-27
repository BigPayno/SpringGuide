package com.payno.jpa.util;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.boot.registry.classloading.spi.ClassLoaderService;
import org.hibernate.boot.registry.classloading.spi.ClassLoadingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.UUIDGenerationStrategy;
import org.hibernate.id.uuid.StandardRandomStrategy;
import org.hibernate.internal.CoreLogging;
import org.hibernate.internal.CoreMessageLogger;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.hibernate.type.descriptor.java.UUIDTypeDescriptor;

import java.io.Serializable;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @author payno
 * @date 2019/11/27 16:02
 * @description
 */
public class UUIDGenerator implements IdentifierGenerator, Configurable {
    private static final CoreMessageLogger LOG = CoreLogging.messageLogger(UUIDGenerator.class);
    private static final Pattern pattern = Pattern.compile("-");
    private UUIDGenerationStrategy strategy;
    private UUIDTypeDescriptor.ValueTransformer valueTransformer;

    private String entityName;

    public UUIDGenerator() {
    }

    public static UUIDGenerator buildSessionFactoryUniqueIdentifierGenerator() {
        UUIDGenerator generator = new UUIDGenerator();
        generator.strategy = StandardRandomStrategy.INSTANCE;
        generator.valueTransformer = UUIDTypeDescriptor.ToStringTransformer.INSTANCE;
        return generator;
    }

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        this.strategy = (UUIDGenerationStrategy) params.get("uuid_gen_strategy");
        if (this.strategy == null) {
            String strategyClassName = params.getProperty("uuid_gen_strategy_class");
            if (strategyClassName != null) {
                try {
                    ClassLoaderService ignore = (ClassLoaderService) serviceRegistry.getService(ClassLoaderService.class);
                    @SuppressWarnings("rawtypes")
                    Class strategyClass = ignore.classForName(strategyClassName);

                    try {
                        this.strategy = (UUIDGenerationStrategy) strategyClass.newInstance();
                    } catch (Exception var8) {
                        LOG.unableToInstantiateUuidGenerationStrategy(var8);
                    }
                } catch (ClassLoadingException var9) {
                    LOG.unableToLocateUuidGenerationStrategy(strategyClassName);
                }
            }
        }

        if (this.strategy == null) {
            this.strategy = StandardRandomStrategy.INSTANCE;
        }

        if (UUID.class.isAssignableFrom(type.getReturnedClass())) {
            this.valueTransformer = UUIDTypeDescriptor.PassThroughTransformer.INSTANCE;
        } else if (String.class.isAssignableFrom(type.getReturnedClass())) {
            this.valueTransformer = UUIDTypeDescriptor.ToStringTransformer.INSTANCE;
        } else {
            if (!byte[].class.isAssignableFrom(type.getReturnedClass())) {
                throw new HibernateException("Unanticipated return type [" + type.getReturnedClass().getName() + "] for UUID conversion");
            }

            this.valueTransformer = UUIDTypeDescriptor.ToBytesTransformer.INSTANCE;
        }

        this.entityName = params.getProperty("entity_name");
        if (this.entityName == null) {
            throw new MappingException("no entity name");
        }
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        Serializable id = session.getEntityPersister(this.entityName, object).getIdentifier(object, session);
        if (id == null) {
            id = this.valueTransformer.transform(this.strategy.generateUUID(session));

            return pattern.matcher((String) id).replaceAll("");
        } else {
            return id;
        }
    }
}
