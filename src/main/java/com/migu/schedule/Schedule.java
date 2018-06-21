package com.migu.schedule;


import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.Server;
import com.migu.schedule.info.TaskInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*
*类名和方法不能修改
 */
public class Schedule {
    
    // 服务节点列表（节点id，服务）
    private Map<Integer, Server> serverMap = new HashMap<Integer, Server>();
    // 任务集合（任务id，消耗率）
    private Map<Integer, Integer> taskMap = new HashMap<Integer, Integer>();
    // 服务下对应任务列表
    private Map<Integer, List<TaskInfo>> servertaskmap = new HashMap<>();
    // 挂起任务列表
    List<TaskInfo> taskinfoLists = new ArrayList<TaskInfo>();
    
    // 系统初始化
    public int init() {
        // 清空服务信息
        serverMap.clear();
        if (serverMap.isEmpty())
        {
            return ReturnCodeKeys.E001;
        }
        return ReturnCodeKeys.E000;
    }

    // 服务节点注册
    public int registerNode(int nodeId)
    {
        // 如果服务节点编号小于等于0
        if (nodeId <= 0)
        {
            return ReturnCodeKeys.E004;
        }
        Iterator<Integer> keys = serverMap.keySet().iterator();
        while (keys.hasNext())
        {
            Integer key = keys.next();
            if (nodeId == key)
            {
                // 如果服务节点编号已注册
                return ReturnCodeKeys.E005;
            }
        }
        Server server = new Server();
        serverMap.put(nodeId, server);
        if (serverMap.get(nodeId) != null)
        {
            // 注册成功
            return ReturnCodeKeys.E003;
        }
        return ReturnCodeKeys.E000;
    }

    // 服务节点注销
    public int unregisterNode(int nodeId)
    {
        // 如果服务节点编号小于等于0
        if (nodeId <= 0)
        {
            return ReturnCodeKeys.E004;
        }
        if (serverMap.get(nodeId).equals(null))
        {
            return ReturnCodeKeys.E007;
        }
        serverMap.remove(nodeId);
        return ReturnCodeKeys.E006;
    }

    // 添加任务
    public int addTask(int taskId, int consumption)
    {
        if (taskId <= 0)
        {
            return ReturnCodeKeys.E009;
        }
        Iterator<Integer> keys = taskMap.keySet().iterator();
        while (keys.hasNext())
        {
            Integer key = keys.next();
            if (taskId == key)
            {
                // 如果相同任务编号任务已经被添加
                return ReturnCodeKeys.E010;
            }
        }
        taskMap.put(taskId, consumption);
        if (serverMap.get(taskId) != null)
        {
            // 添加成功
            return ReturnCodeKeys.E008;
        }
        return ReturnCodeKeys.E000;
    }


    public int deleteTask(int taskId) {
        // TODO 方法未实现
        return ReturnCodeKeys.E000;
    }


    public int scheduleTask(int threshold) {
        // TODO 方法未实现
        return ReturnCodeKeys.E000;
    }


    public int queryTaskStatus(List<TaskInfo> tasks) {
        // TODO 方法未实现
        return ReturnCodeKeys.E000;
    }

}
