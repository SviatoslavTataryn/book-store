package mate.academy.bookstore.mapper;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import mate.academy.bookstore.config.MapperConfig;
import mate.academy.bookstore.dto.user.UserRegistrationRequestDto;
import mate.academy.bookstore.dto.user.UserResponseDto;
import mate.academy.bookstore.model.Role;
import mate.academy.bookstore.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", source = "role", qualifiedByName = "mapRoleToSet")
    User toModel(UserRegistrationRequestDto requestDto);

    @Mapping(target = "roles", source = "roles")
    UserResponseDto toUserResponse(User user);

    @Named("mapRoleToSet")
    default Set<Role> mapRoleToSet(String role) {
        if (role == null) {
            return Collections.emptySet();
        }
        Role r = new Role();
        r.setName(Role.RoleName.valueOf(role));
        return Collections.singleton(r);
    }

    default Set<Role> mapRoles(Set<String> roles) {
        return roles.stream()
                .map(role -> {
                    Role r = new Role();
                    r.setName(Role.RoleName.valueOf(role));
                    return r;
                })
                .collect(Collectors.toSet());
    }

    default Set<String> mapRoleNames(Set<Role> roles) {
        return roles.stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet());
    }
}
