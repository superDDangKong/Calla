package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.AlarmVO;

public interface AlarmDAO {
	
	List<AlarmVO> select(String memberNickname);
	int insert(AlarmVO vo);
	int update(int alarmId);
	int check(String memberNickname);
	int delete(int alarmId);
}
