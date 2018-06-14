package com.u2u.ibms.web.project.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.framework.base.BaseService;
import com.u2u.ibms.common.beans.ProjectPicture;
import com.u2u.ibms.common.mapper.ProjectPictureMapper;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ProjectPictureService extends BaseService {

	@Autowired
	private ProjectPictureMapper projectPictureMapper;

	public List<ProjectPicture> getAll(RowBounds rb, int projectId) {
		return projectPictureMapper.getAll(rb, projectId);
	}

}
