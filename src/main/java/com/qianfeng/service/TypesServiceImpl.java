package com.qianfeng.service;

import com.qianfeng.dao.TypesMapper;
import com.qianfeng.pojo.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypesServiceImpl implements TypesService {

    @Autowired
    private TypesMapper typesMapper;

    @Override
    public List<Types> getTypesList() {
        //1. 查询所有的类型
        List<Types> typesList = typesMapper.selectAll();
        //2. 定义一个空集合,用来存储最终返回的数据(长度肯定是2)
        List<Types> finalTypesList = new ArrayList<>();
        //定义集合存储所有的父菜单
        ArrayList<Types> parentList = new ArrayList<>();
        //3. 遍历整个类型的集合
        for (Types type:typesList){
            //判断type是不是一级菜单
            if(type.getPid()==null){
                //将一级菜单添加到最终集合中去
                finalTypesList.add(type);
                //一级菜单也可能是别人的父菜单
                parentList.add(type);
            }else{
                //不是一级，可能是2 3 级
                //遍历父菜单集合，将当前不是一级菜单的菜单跟父菜单的元素比较
                for(Types parent:parentList){
                    //将当前菜单的pid和所有父菜单的tid比较，找出他的父菜单
                    if(parent.getTid().equals(type.getPid())){
                        parent.getChildTypes().add(type);
                        //这个菜单虽然不是一级的，但是也有可能是别人的父菜单
                        parentList.add(type);
                        break;
                    }
                }
            }
        }

        return finalTypesList;
    }
}
