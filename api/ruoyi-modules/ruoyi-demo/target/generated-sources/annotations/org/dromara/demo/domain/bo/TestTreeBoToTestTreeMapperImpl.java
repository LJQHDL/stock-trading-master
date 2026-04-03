package org.dromara.demo.domain.bo;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.dromara.demo.domain.TestTree;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-04T00:25:27+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260224-0835, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class TestTreeBoToTestTreeMapperImpl implements TestTreeBoToTestTreeMapper {

    @Override
    public TestTree convert(TestTreeBo arg0) {
        if ( arg0 == null ) {
            return null;
        }

        TestTree testTree = new TestTree();

        testTree.setSearchValue( arg0.getSearchValue() );
        testTree.setCreateDept( arg0.getCreateDept() );
        testTree.setCreateBy( arg0.getCreateBy() );
        testTree.setCreateTime( arg0.getCreateTime() );
        testTree.setUpdateBy( arg0.getUpdateBy() );
        testTree.setUpdateTime( arg0.getUpdateTime() );
        Map<String, Object> map = arg0.getParams();
        if ( map != null ) {
            testTree.setParams( new LinkedHashMap<String, Object>( map ) );
        }
        testTree.setDeptId( arg0.getDeptId() );
        testTree.setId( arg0.getId() );
        testTree.setParentId( arg0.getParentId() );
        testTree.setTreeName( arg0.getTreeName() );
        testTree.setUserId( arg0.getUserId() );

        return testTree;
    }

    @Override
    public TestTree convert(TestTreeBo arg0, TestTree arg1) {
        if ( arg0 == null ) {
            return arg1;
        }

        arg1.setSearchValue( arg0.getSearchValue() );
        arg1.setCreateDept( arg0.getCreateDept() );
        arg1.setCreateBy( arg0.getCreateBy() );
        arg1.setCreateTime( arg0.getCreateTime() );
        arg1.setUpdateBy( arg0.getUpdateBy() );
        arg1.setUpdateTime( arg0.getUpdateTime() );
        if ( arg1.getParams() != null ) {
            Map<String, Object> map = arg0.getParams();
            if ( map != null ) {
                arg1.getParams().clear();
                arg1.getParams().putAll( map );
            }
            else {
                arg1.setParams( null );
            }
        }
        else {
            Map<String, Object> map = arg0.getParams();
            if ( map != null ) {
                arg1.setParams( new LinkedHashMap<String, Object>( map ) );
            }
        }
        arg1.setDeptId( arg0.getDeptId() );
        arg1.setId( arg0.getId() );
        arg1.setParentId( arg0.getParentId() );
        arg1.setTreeName( arg0.getTreeName() );
        arg1.setUserId( arg0.getUserId() );

        return arg1;
    }
}
