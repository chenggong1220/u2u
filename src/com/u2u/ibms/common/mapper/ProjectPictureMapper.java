package com.u2u.ibms.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.ibms.common.beans.ProjectPicture;

public interface ProjectPictureMapper {

	List<ProjectPicture> getAll(RowBounds rb, @Param("projectId") int projectId);

	ProjectPicture getByProjectIdAndIndex(@Param("projectId") int projectId,
			@Param("pictureIndex") int pictureIndex);

	ProjectPicture getById(@Param("id") int id);

	void insert(ProjectPicture projectPicture);

	void update(ProjectPicture projectPicture);

	void delete(@Param("projectId") int projectId);
}
