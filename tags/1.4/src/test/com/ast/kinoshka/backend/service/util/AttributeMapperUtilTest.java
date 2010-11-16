package com.ast.kinoshka.backend.service.util;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import com.ast.kinoshka.backend.data.ActorMapper;
import com.ast.kinoshka.backend.data.BoxMapper;
import com.ast.kinoshka.backend.data.DirectorMapper;
import com.ast.kinoshka.backend.data.GenreMapper;
import com.ast.kinoshka.backend.data.RegionMapper;
import com.ast.kinoshka.backend.data.YearMapper;
import com.ast.kinoshka.backend.model.AttributeCategory;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

/**
 * Unit test for AttributeMapperUtil.
 * @author Aleh_Stsiapanau
 *
 */
public class AttributeMapperUtilTest {

  private SqlSession sessionMock;

  @Before
  public void setUp() {
    this.sessionMock = createMock(SqlSession.class);
  }

  @Test
  public void testGetMapperByCategory() {
    expect(sessionMock.getMapper(ActorMapper.class)).andReturn(createMock(ActorMapper.class));
    expect(sessionMock.getMapper(BoxMapper.class)).andReturn(createMock(BoxMapper.class));
    expect(sessionMock.getMapper(RegionMapper.class)).andReturn(createMock(RegionMapper.class));
    expect(sessionMock.getMapper(DirectorMapper.class)).andReturn(createMock(DirectorMapper.class));
    expect(sessionMock.getMapper(GenreMapper.class)).andReturn(createMock(GenreMapper.class));
    expect(sessionMock.getMapper(YearMapper.class)).andReturn(createMock(YearMapper.class));

    replay(sessionMock);

    AttributeMapperUtil.getMapperByCategory(sessionMock, AttributeCategory.ACTOR);
    AttributeMapperUtil.getMapperByCategory(sessionMock, AttributeCategory.BOX);
    AttributeMapperUtil.getMapperByCategory(sessionMock, AttributeCategory.COUNTRY);
    AttributeMapperUtil.getMapperByCategory(sessionMock, AttributeCategory.DIRECTOR);
    AttributeMapperUtil.getMapperByCategory(sessionMock, AttributeCategory.GENRE);
    AttributeMapperUtil.getMapperByCategory(sessionMock, AttributeCategory.YEAR);

    verify(sessionMock);
  }

  @Test(expected=NullPointerException.class)
  public void testGetMapperByNull() {
    Assert.assertNull(AttributeMapperUtil.getMapperByCategory(sessionMock, null));
  }

  @Test(expected=NullPointerException.class)
  public void testGetMapperWithNullSession() {
    Assert.assertNull(AttributeMapperUtil.getMapperByCategory(null, AttributeCategory.ACTOR));
  }


}
