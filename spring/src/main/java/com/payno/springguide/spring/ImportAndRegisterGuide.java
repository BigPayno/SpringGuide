package com.payno.springguide.spring;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author payno
 * @date 2019/11/21 21:39
 * @description
 *      implements ImportSelector
 *                  ImportBeanDefinitionRegistrar
 *      @Import
 *      @ImportRegister
 *      see @SpringClassLoaderGuide
 */
public class ImportAndRegisterGuide {
    public static class CusImportSelector implements ImportSelector{
        @Override
        public String[] selectImports(AnnotationMetadata annotationMetadata) {
            return new String[]{

            };
        }
    }
}
