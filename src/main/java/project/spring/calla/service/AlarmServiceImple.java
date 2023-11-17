package project.spring.calla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.calla.domain.AlarmVO;
import project.spring.calla.persistence.AlarmDAO;


@Service
public class AlarmServiceImple implements AlarmService {
		private static final Logger logger = 
				LoggerFactory.getLogger(AlarmServiceImple.class);
		
		@Autowired
		private AlarmDAO alarmDAO;
		
		@Override
		public List<AlarmVO> read(String memberNickname) {
			logger.info("read() 호출 : memberNickname = " + memberNickname);
			return alarmDAO.select(memberNickname);
		}

		@Override
		public int create(AlarmVO vo) {
			logger.info("read() 호출 : vo = " + vo.toString());
			return alarmDAO.insert(vo);
		}

		@Override
		public int update(int alarmId) {
			logger.info("update() 호출 : alarmId = " + alarmId);
			return alarmDAO.update(alarmId);
		}

    

}
