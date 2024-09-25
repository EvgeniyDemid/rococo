package data.entity;


import lombok.Getter;
import lombok.Setter;
import model.UserJson;

import java.util.UUID;

@Getter
@Setter
public class UserEntity {

    private UUID id;
    private String username;
    private String firstname;
    private String lastname;
    private byte[] avatar;

    public UserEntity fromJson(UserJson userJson){
    UserEntity userEntity = new UserEntity();
    userEntity.setId(userJson.id());
    userEntity.setUsername(userJson.username());
    userEntity.setFirstname(userJson.firstname());
    userEntity.setLastname(userJson.lastname());
    userEntity.setAvatar(null);
    return userEntity;
    }
}
