package com.learner.service.pojoService;

import com.learner.entity.model.MaimaiUser;
import com.learner.service.baseService.IBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liufangliang on 2018/3/21.
 */
@Slf4j
@Service
public class MaimaiService {
    @Autowired
    private IBaseService baseService;

    public void saveMaimaiUser(MaimaiUser user){
        if(user!=null){
            try {
                baseService.create(user);
            } catch (Exception e) {
                log.error("save {} exception : {}",MaimaiUser.class.getName(),e);
            }
        }
    }

    public void saveUsers(List<MaimaiUser> users){
        if(users!=null && users.size()>0){
            int start =0;
            int stepLength=999;
            while (start<users.size()){
                int end =Math.min(start+stepLength,users.size());
                List<MaimaiUser > users1=users.subList(start,end);
                try {
                    baseService.batchCreate(users1);
                } catch (Exception e) {
                    log.error("insert {} list to table exception ! detail : {}",MaimaiUser.class.getSimpleName(),e);
                }
                start=end;
            }
        }else {
            log.info("there is no {} to save!",MaimaiUser.class.getName());
        }
    }
}
