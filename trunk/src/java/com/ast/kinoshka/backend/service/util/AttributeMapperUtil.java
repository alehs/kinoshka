package com.ast.kinoshka.backend.service.util;

import com.ast.kinoshka.backend.data.ActorMapper;
import com.ast.kinoshka.backend.data.AttributeMapper;
import com.ast.kinoshka.backend.data.BoxMapper;
import com.ast.kinoshka.backend.data.DirectorMapper;
import com.ast.kinoshka.backend.data.DiskMapper;
import com.ast.kinoshka.backend.data.GenreMapper;
import com.ast.kinoshka.backend.data.RegionMapper;
import com.ast.kinoshka.backend.data.YearMapper;
import com.ast.kinoshka.backend.model.AttributeCategory;
import com.google.common.base.Preconditions;

import org.apache.ibatis.session.SqlSession;

/**
 * 
 * @author Aleh_Stsiapanau
 */
public class AttributeMapperUtil {
  private AttributeMapperUtil() {/* prevent instantiation.*/}

  /**
   * 
   * @param category
   * @return
   */
  public static AttributeMapper getMapperByCategory(final SqlSession session,
      final AttributeCategory category) {
    Preconditions.checkNotNull(session);
    Preconditions.checkNotNull(category);

    AttributeMapper mapper = null;
    switch (category) {
    case GENRE:
      mapper = session.getMapper(GenreMapper.class);
      break;
    case ACTOR:
      mapper = session.getMapper(ActorMapper.class);
      break;
    case DIRECTOR:
      mapper = session.getMapper(DirectorMapper.class);
      break;
    case COUNTRY:
      mapper = session.getMapper(RegionMapper.class);
      break;
    case YEAR:
      mapper = session.getMapper(YearMapper.class);
      break;
    case BOX:
      mapper = session.getMapper(BoxMapper.class);
      break;
    case DISK:
      mapper = session.getMapper(DiskMapper.class);
      break;
    }
    return mapper;
  }
}
