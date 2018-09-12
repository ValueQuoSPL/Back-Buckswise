package com.valuequo.buckswise.service.mapper;
import org.mapstruct.Mapper;
import com.valuequo.buckswise.domain.MyProfile;
import com.valuequo.buckswise.service.dto.MyProfileDTO;

	
@Mapper(componentModel = "spring", uses = {})
public interface MyProfileMapper extends EntityMapper<MyProfileDTO, MyProfile> {

    default MyProfile fromId(Long id) {
        if (id == null) {
            return null;
        }
        MyProfile myProfile = new MyProfile();
        myProfile.setId(id);
        return myProfile;
    }
}


