package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TaskDao;
import com.example.demo.entity.Task;

@Service
public class TaskServiceImpl implements TaskService {

	private final TaskDao dao;

	// DAOの読み込み
	public TaskServiceImpl(TaskDao dao) {
		this.dao = dao;
	}

	//Listの取得 dao.findAll();だけでOK
	@Override
	public List<Task> findAll() {
		return dao.findAll();
	}

	@Override
	public Optional<Task> getTask(int id) {

		//Optional<Task>一件を取得 idが無ければ例外発生　
		try {
			return dao.findById(id);
		// タスクを一件取得(queryForMap)で例外が発生した時
		} catch (EmptyResultDataAccessException e) {
			//自作の例外(TaskNotFoundException)を出す
			throw new TaskNotFoundException("指定されたタスクが存在しません");
		}

	}

	@Override
	public void insert(Task task) {
		dao.insert(task);
	}

	//Daoの方は件数が返ってくる → 0だった場合に例外を発生させる
	@Override
	public void update(Task task) {

		//Taskを更新　idが無ければ例外発生
		if(dao.update(task) == 0) {
			throw new TaskNotFoundException("更新するタスクが存在しません");
		}

	}

	@Override
	public void deleteById(int id) {

		//Taskを更新 idがなければ例外発生
		if(dao.deleteById(id) == 0) {
			throw new TaskNotFoundException("削除するタスクが存在しません");
		}

	}

	@Override
	public List<Task> findByType(int typeId) {
		//2-3 typeIdを引数に指定してdaoのfindByType実行し、結果をreturnする
		return null;
	}
}
