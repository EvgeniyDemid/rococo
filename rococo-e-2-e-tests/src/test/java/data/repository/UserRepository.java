package data.repository;

import data.entity.UserAuthEntity;
import data.entity.UserEntity;

import java.util.UUID;

public interface UserRepository {

	UserAuthEntity createUserInAuth(UserAuthEntity userAuthEntity);

	UserEntity createUserInUserdata(UserEntity userEntity);

	UserEntity findUserInUserdataById(UUID id);

	UserEntity findUserInUserdataByUserName(String username);

	void deleteUserByUserName(String username);
}
