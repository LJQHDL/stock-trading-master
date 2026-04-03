package org.dromara.system.domain.vo;

import javax.annotation.processing.Generated;
import org.dromara.system.domain.SysPost;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-13T13:36:04+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class SysPostVoToSysPostMapperImpl implements SysPostVoToSysPostMapper {

    @Override
    public SysPost convert(SysPostVo arg0) {
        if ( arg0 == null ) {
            return null;
        }

        SysPost sysPost = new SysPost();

        sysPost.setCreateTime( arg0.getCreateTime() );
        sysPost.setDeptId( arg0.getDeptId() );
        sysPost.setPostCategory( arg0.getPostCategory() );
        sysPost.setPostCode( arg0.getPostCode() );
        sysPost.setPostId( arg0.getPostId() );
        sysPost.setPostName( arg0.getPostName() );
        sysPost.setPostSort( arg0.getPostSort() );
        sysPost.setRemark( arg0.getRemark() );
        sysPost.setStatus( arg0.getStatus() );

        return sysPost;
    }

    @Override
    public SysPost convert(SysPostVo arg0, SysPost arg1) {
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
