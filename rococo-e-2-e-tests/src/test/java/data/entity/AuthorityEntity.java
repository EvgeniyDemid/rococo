package data.entity;


import enums.Authority;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class AuthorityEntity implements Serializable {

    private UUID id;
    private Authority authority;
    private UserEntity user;

}
