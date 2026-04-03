package org.dromara.demo.domain.vo;

import javax.annotation.processing.Generated;
import org.dromara.demo.domain.TestTree;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-04T00:25:27+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260224-0835, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class TestTreeVoToTestTreeMapperImpl implements TestTreeVoToTestTreeMapper {

    @Override
    public TestTree convert(TestTreeVo arg0) {
        if ( arg0 == null ) {
            return null;
        }

        TestTree testTree = new TestTree();

        testTree.setCreateTime( arg0.getCreateTime() );
        testTree.setDeptId( arg0.getDeptId() );
        testTree.setId( arg0.getId() );
        testTree.setParentId( arg0.getParentId() );
        testTree.setTreeName( arg0.getTreeName() );
        testTree.setUserId( arg0.getUserId() );

        return testTree;
    }

    @Override
    public TestTree convert(TestTreeVo arg0, TestTree arg1) {
        if ( arg0 == null ) {
            return arg1;
        }

        arg1.setCreateTime( arg0.getCreateTime() );
        arg1.setDeptId( arg0.getDeptId() );
        arg1.setId( arg0.getId() );
        arg1.setParentId( arg0.getParentId() );
        arg1.setTreeName( arg0.getTreeName() );
        arg1.setUserId( arg0.getUserId() );

        return arg1;
    }
}
