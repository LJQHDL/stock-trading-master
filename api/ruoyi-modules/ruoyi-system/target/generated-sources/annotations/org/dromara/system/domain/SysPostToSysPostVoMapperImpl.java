package org.dromara.system.domain;

import javax.annotation.processing.Generated;
import org.dromara.system.domain.vo.SysPostVo;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-13T13:36:00+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class SysPostToSysPostVoMapperImpl implements SysPostToSysPostVoMapper {

    @Override
    public SysPostVo convert(SysPost arg0) {
        if ( arg0 == null ) {
            return null;
        }

        SysPostVo sysPostVo = new SysPostVo();

        sysPostVo.setCreateTime( arg0.getCreateTime() );
        sysPostVo.setDeptId( arg0.getDeptId() );
        sysPostVo.setPostCategory( arg0.getPostCategory() );
        sysPostVo.setPostCode( arg0.getPostCode() );
        sysPostVo.setPostId( arg0.getPostId() );
        sysPostVo.setPostName( arg0.getPostName() );
        sysPostVo.setPostSort( arg0.getPostSort() );
        sysPostVo.setRemark( arg0.getRemark() );
        sysPostVo.setStatus( arg0.getStatus() );

        return sysPostVo;
    }

    @Override
    public SysPostVo convert(SysPost arg0, SysPostVo arg1) {
        if ( arg0 == null ) {
            return arg1;
        }

        arg1.setCreateTime( arg0.getCreateTime() );
        arg1.setDeptId( arg0.getDeptId() );
        arg1.setPostCategory( arg0.getPostCategory() );
        arg1.setPostCode( arg0.getPostCode() );
        arg1.setPostId( arg0.getPostId() );
        arg1.setPostName( arg0.getPostName() );
        arg1.setPostSort( arg0.getPostSort() );
        arg1.setRemark( arg0.getRemark() );
        arg1.setStatus( arg0.getStatus() );

        return arg1;
    }
}
