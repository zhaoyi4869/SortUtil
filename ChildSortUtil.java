package com.zorkdata.traceserver.util;

import com.zorkdata.traceserver.po.NodeChildPo;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChildSortUtil {
    private List<NodeChildPo> resultList = new ArrayList<>();  //输出列表
    private List<NodeChildPo> nodeChildPoList;  //输入列表

    /**
     * 排序
     * @param nodeChildPoList
     */
    public ChildSortUtil(List<NodeChildPo> nodeChildPoList){
        this.nodeChildPoList = nodeChildPoList;

        for(NodeChildPo nodeChildPo : this.nodeChildPoList){
            if("0000000000000000".equals(nodeChildPo.getParentId())){  //当父级为空
                resultList.add(nodeChildPo);  //当父级为空时即顶级，首先放入输出列表
                findChildren(nodeChildPo);  //查询下级
            }
        }
    }

    /**
     * 查询下级
     * @param dept
     */
    private void findChildren(NodeChildPo dept){
        List<NodeChildPo> childrenList = new ArrayList<>();
        //遍历输入列表，查询下级
        for(NodeChildPo d : nodeChildPoList){
            if(Objects.equals(dept.getSpanId(), d.getParentId()))
                childrenList.add(d);
        }
        //遍历到最末端，无下级，退出遍历
        if(childrenList.isEmpty()){
            return;
        }
        //对下级进行遍历
        for(NodeChildPo d : childrenList){
            resultList.add(d);
            findChildren(d);
        }
    }

    public List<NodeChildPo> getResultList(){
        return resultList;
    }

    public static List<NodeChildPo> sort(List<NodeChildPo> originalList){
        return new ChildSortUtil(originalList).getResultList();
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        List<NodeChildPo> originalList = new ArrayList<>();
        NodeChildPo dept = new NodeChildPo();

        dept = new NodeChildPo();
        dept.setSpanId("122");
//        dept.setName("三级b");
        dept.setParentId("12");
        originalList.add(dept);

        dept = new NodeChildPo();
        dept.setSpanId("13");
//        dept.setName("二级b");
        dept.setParentId("1");
        originalList.add(dept);

        dept = new NodeChildPo();
        dept.setSpanId("121");
//        dept.setName("三级a");
        dept.setParentId("12");
        originalList.add(dept);

        dept = new NodeChildPo();
        dept.setSpanId("1");
//        dept.setName("一级");
        originalList.add(dept);

        dept = new NodeChildPo();
        dept.setSpanId("131");
//        dept.setName("三级c");
        dept.setParentId("13");
        originalList.add(dept);

        dept = new NodeChildPo();
        dept.setSpanId("12");
//        dept.setName("二级a");
        dept.setParentId("1");
        originalList.add(dept);

        dept = new NodeChildPo();
        dept.setSpanId("132");
//        dept.setName("三级d");
        dept.setParentId("13");
        originalList.add(dept);

        List<NodeChildPo> resultList = sort(originalList);
        System.out.println("输入列表："+ originalList);
        System.out.println("输出列表："+ resultList);
    }
}
