package com.payno.jpa.base.repo;

import com.payno.jpa.base.entity.namegraph.PythonInputKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author payno
 * @date 2019/7/12 15:11
 * @description
 */
public interface PythonInputKeyRepo extends JpaRepository<PythonInputKey, String> {
    /**
     * findByPythonScriptId
     *
     * @param id
     * @author: payno
     * @time: 2019/7/23 08:50
     * @description:
     * @return: java.util.List<com.gz.parser.python.entity.PythonInputKey>
     */
    List<PythonInputKey> findByPythonScriptId(String id);

    /**
     * deleteBatch
     *
     * @param ids
     * @author: payno
     * @time: 2019/7/23 08:47
     * @description: 批量删除，一条语句
     * @return: void
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query("delete from PythonInputKey ik where ik.inputKeyId in (?1)")
    void deleteBatch(List<String> ids);

    /**
     * deleteByPythonScriptIdBatch
     *
     * @param id
     * @author: payno
     * @time: 2019/7/23 08:52
     * @description: 批量删除，一条sql语句
     * @return: void
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query("delete from PythonInputKey ik where ik.pythonScriptId = (?1)")
    void deleteByPythonScriptIdBatch(String id);

    /**
     * deleteByPyAndPythonScriptIdAndAndInputKeyName
     *
     * @param id
     * @param keyName
     * @author: payno
     * @time: 2019/7/23 08:55
     * @description: 删除某id脚本的某个参数, 必须声明是删除,否则执行的还是select
     * @return: void
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query("delete from PythonInputKey ik where ik.pythonScriptId = (?1) and ik.inputKeyName = (?2)")
    void deleteByPythonScriptIdAndAndInputKeyName(String id, String keyName);
}
