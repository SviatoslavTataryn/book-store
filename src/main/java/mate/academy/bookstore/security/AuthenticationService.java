package mate.academy.bookstore.security;

import java.util.Optional;
import lombok.AllArgsConstructor;
import mate.academy.bookstore.dto.user.UserRegistrationRequestDto;
import mate.academy.bookstore.model.User;
import mate.academy.bookstore.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    public boolean authenticate(UserRegistrationRequestDto requestDto) {
        Optional<User> user = userRepository.findUserByEmail(requestDto.email());
        return (user.isPresent() && user.get().getPassword().equals(requestDto.password()));
    }
}
