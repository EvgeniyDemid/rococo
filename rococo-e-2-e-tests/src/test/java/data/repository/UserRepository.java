package data.repository;

import data.entity.UserAuthEntity;
import data.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

	UserAuthEntity createUserInAuth(UserAuthEntity userAuthEntity);

	UserEntity createUserInUserdata(UserEntity userEntity);

	Optional<UserEntity> findUserInUserdataById(UUID id);
}
