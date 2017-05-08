package com.tanwuyu.ivrtym.seibertronrecyclerview;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivrty on 2017/5/4.
 */

public class TreeNode {
    TreeNode parentNode;

    //返回当前节点下直接子节点集合:这里没实现
    List<TreeNode> getChildList() {
        return null;
    }

    //返回当前节点下所有晚辈节点集合
    List<TreeNode> getJuniors() {
        List<TreeNode> juniorList = new ArrayList<TreeNode>();
        List<TreeNode> childList = this.getChildList();
        if (childList == null) {
            return juniorList;
        } else {
            int childNumber = childList.size();
            //递归遍历
            for (int i = 0; i < childNumber; i++) {
                TreeNode junior = childList.get(i);
                juniorList.add(junior);
                juniorList.addAll(junior.getJuniors());
            }
            return juniorList;
        }
    }
}
