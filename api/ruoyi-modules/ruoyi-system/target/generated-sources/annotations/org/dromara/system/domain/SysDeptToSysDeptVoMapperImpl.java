package org.dromara.system.domain;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.dromara.system.domain.vo.SysDeptVo;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-13T13:36:01+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class SysDeptToSysDeptVoMapperImpl implements SysDeptToSysDeptVoMapper {

    @Override
    public SysDeptVo convert(SysDept arg0) {
        if ( arg0 == null ) {
            return null;
        }

        SysDeptVo sysDeptVo = new SysDeptVo();

        sysDeptVo.setAncestors( arg0.getAncestors() );
        List<SysDept> list = arg0.getChildren();
        if ( list != null ) {
            sysDeptVo.setChildren( new ArrayList<SysDept>( list ) );
        }
        sysDeptVo.setCreateTime( arg0.getCreateTime() );
        sysDeptVo.setDeptCategory( arg0.getDeptCategory() );
        sysDeptVo.setDeptId( arg0.getDeptId() );
        sysDeptVo.setDeptName( arg0.getDeptName() );
        sysDeptVo.setEmail( arg0.getEmail() );
        sysDeptVo.setLeader( arg0.getLeader() );
        sysDeptVo.setOrderNum( arg0.getOrderNum() );
        sysDeptVo.setParentId( arg0.getParentId() );
        sysDeptVo.setPhone( arg0.getPhone() );
        sysDeptVo.setStatus( arg0.getStatus() );

        return sysDeptVo;
    }

    @Override
    public SysDeptVo convert(SysDept arg0, SysDeptVo arg1) {
        if ( arg0 == null ) {
            return arg1;
        }

        arg1.setAncestors( arg0.getAncestors() );
        if ( arg1.getChildren() != null ) {
            List<SysDept> list = arg0.getChildren();
            if ( list != null ) {
                arg1.getChildren().clear();
                arg1.getChildren().addAll( list );
            }
            else {
                arg1.setChildren( null );
            }
        }
        else {
            List<SysDept> list = arg0.getChildren();
            if ( list != null ) {
                arg1.setChildren( new ArrayList<SysDept>( list ) );
            }
        }
        arg1.setCreateTime( arg0.getCreateTime() );
        arg1.setDeptCategory( arg0.getDeptCategory() );
        arg1.setDeptId( arg0.getDeptId() );
        arg1.setDeptName( arg0.getDeptName() );
        arg1.setEmail( arg0.getEmail() );
        arg1.setLeader( arg0.getLeader() );
        arg1.setOrderNum( arg0.getOrderNum() );
        arg1.setParentId( arg0.getParentId() );
        arg1.setPhone( arg0.getPhone() );
        arg1.setStatus( arg0.getStatus() );

        return arg1;
    }
}
