package com.payno.jpa.base.repo;

import com.payno.jpa.base.entity.namegraph.PythonScript;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author payno
 * @date 2019/7/12 15:09
 * @description
 */
public interface PythonScriptRepo extends JpaRepository<PythonScript, String> {
    /**
     * findByPythonScriptName
     *
     * @param pythonScriptName
     * @author: payno
     * @time: 2019/7/17 11:17
     * @description: base
     * @return: java.util.Optional<com.gz.parser.python.entity.PythonScript>
     */
    Optional<PythonScript> findByPythonScriptName(String pythonScriptName);

    /**
     * findByPythonScriptIdOrPythonScriptName
     *
     * @param id
     * @param name
     * @author: payno
     * @time: 2019/7/17 11:18
     * @description: 使用id或姓名查找，带着数据源
     * @return: java.util.Optional<com.gz.parser.python.entity.PythonScript>
     */
    @EntityGraph(value = "PythonScript.Graph", type = EntityGraph.EntityGraphType.FETCH)
    Optional<PythonScript> findByPythonScriptIdOrPythonScriptName(String id, String name);

    @Override
    @EntityGraph(value = "PythonScript.Graph", type = EntityGraph.EntityGraphType.FETCH)
    List<PythonScript> findAll();
}
