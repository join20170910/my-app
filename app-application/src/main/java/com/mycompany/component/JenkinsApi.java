package com.mycompany.component;

import com.mycompany.conf.JenkinsConfig;
import com.offbytwo.jenkins.model.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author john
 */
@Component
public class JenkinsApi {

    // Jenkins 对象
    @Resource
    private JenkinsConfig jenkinsServer;

    /**
     * 获取主机信息
     */
    public void getComputerInfo() {
        try {
            Map<String, Computer> map = jenkinsServer.connection().getComputers();
            for (Computer computer : map.values()) {
                // 获取当前节点-节点名称
                System.out.println(computer.details().getDisplayName());
                // 获取当前节点-执行者数量
                System.out.println(computer.details().getNumExecutors());
                // 获取当前节点-执行者详细信息
                List<Executor> executorList = computer.details().getExecutors();
                // 查看当前节点-是否脱机
                System.out.println(computer.details().getOffline());
                // 获得节点的全部统计信息
                LoadStatistics loadStatistics = computer.details().getLoadStatistics();
                // 获取节点的-监控数据
                Map<String, Map> monitorData = computer.details().getMonitorData();
                //......
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重启 Jenkins
     */
    public void restart() {
        try {
            jenkinsServer.connection().restart(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 安全重启 Jenkins
     */
    public void safeRestart() {
        try {
            jenkinsServer.connection().safeRestart(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 安全结束 Jenkins
     */
    public void safeExit() {
        try {
            jenkinsServer.connection().safeExit(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭 Jenkins 连接
     */
    public void close() {
        jenkinsServer.connection().close();
    }

    /**
     * 根据 Label 查找代理节点信息
     */
    public void getLabelNodeInfo(String node) {
        try {
            //LabelWithDetails labelWithDetails = jenkinsServer.connection().getLabel("jnlp-agent");
            LabelWithDetails labelWithDetails = jenkinsServer.connection().getLabel(node);
            // 获取标签名称
            System.out.println(labelWithDetails.getName());
            // 获取 Cloud 信息
            System.out.println(labelWithDetails.getClouds());
            // 获取节点信息
            System.out.println(labelWithDetails.getNodeName());
            // 获取关联的 Job
            System.out.println(labelWithDetails.getTiedJobs());
            // 获取参数列表
            System.out.println(labelWithDetails.getPropertiesList());
            // 是否脱机
            System.out.println(labelWithDetails.getOffline());
            // 获取描述信息
            System.out.println(labelWithDetails.getDescription());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断 Jenkins 是否运行
     */
    public void isRunning() {
        boolean isRunning = jenkinsServer.connection().isRunning();
        System.out.println(isRunning);
    }

    /**
     * 获取 Jenkins 插件信息
     */
    public void getPluginInfo(){
        try {
            PluginManager pluginManager =jenkinsServer.connection().getPluginManager();
            // 获取插件列表
            List<Plugin> plugins = pluginManager.getPlugins();
            for (Plugin plugin:plugins){
                // 插件 wiki URL 地址
                System.out.println(plugin.getUrl());
                // 版本号
                System.out.println(plugin.getVersion());
                // 简称
                System.out.println(plugin.getShortName());
                // 完整名称
                System.out.println(plugin.getLongName());
                // 是否支持动态加载
                System.out.println(plugin.getSupportsDynamicLoad());
                // 插件依赖的组件
                System.out.println(plugin.getDependencies());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
