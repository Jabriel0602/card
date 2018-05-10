package com.card.service.task;

import com.card.dao.TaskDao;
import com.card.domain.task.Task;
import com.card.domain.task.enums.TaskTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDao taskDao;

	@Override
	public int insert(Task task) {
		return taskDao.insert(task);
	}

	@Override
	public int insertSelective(Task task) {
		return taskDao.insertSelective(task);
	}

	@Override
	public int insertList(List<Task> tasks) {
		return taskDao.insertList(tasks);
	}

	@Override
	public int update(Task task) {
		return taskDao.update(task);
	}


	@Override
	public int updateStatus(Long taskId, int oldStatus, int newStatus) {
		return taskDao.updateStatus(taskId, oldStatus, newStatus);
	}

	@Override
	public boolean isFail(Task task) {
		return task.getRetryTimes() >= task.getMaxRetryTimes();
	}

	@Override
	public Task selectTaskById(Long taskId) {
		return taskDao.selectTaskById(taskId);
	}

	@Override
	public List<Task> selectTaskByParam(Task task) {
		return taskDao.selectTaskByParam(task);
	}

	@Override
	public List<Task> selectAllTask() {
		return taskDao.selectAllTask();
	}

	@Override
	public List<Task> selectTaskInitial(int size) {
		return taskDao.selectTaskInitial(size);
	}

	@Override
	public List<Task> selectTaskByOrderId(Long orderId,Integer taskType) {
		return taskDao.selectTaskByOrderId(orderId,taskType);
	}

}
