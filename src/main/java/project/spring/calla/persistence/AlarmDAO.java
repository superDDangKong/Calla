package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.AlarmVO;

public interface AlarmDAO {
	
	List<AlarmVO> select(String hsid);
}
