package kz.u.u.uMe.services.impl;

import kz.u.u.uMe.exceptions.ServiceException;
import kz.u.u.uMe.models.entities.Role;
import kz.u.u.uMe.models.entities.User;
import kz.u.u.uMe.repositories.UserRepository;
import kz.u.u.uMe.services.UserService;
import kz.u.u.uMe.shared.utils.codes.ErrorCode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * @author Assylkhan
 * on 10.04.2019
 * @project logistic_server
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private SessionFactory hibernateFactory;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           EntityManagerFactory factory,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.hibernateFactory = factory.unwrap(SessionFactory.class);
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User findById(Long id) throws ServiceException {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("user not found")
                .build());
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public List<User> findAllWithDeleted() {
        return userRepository.findAll();
    }

    @Override
    public User update(User user) throws ServiceException {
        if(user.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("user is null")
                    .build();
        }
        return  userRepository.save(user);
    }

    @Override
    public User save(User user) throws ServiceException {
        if(user.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("user already exists")
                    .build();
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return  userRepository.save(user);
    }

    @Override
    public void delete(User user) throws ServiceException {
        if(user.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("user is null")
                    .build();
        }
        user = findById(user.getId());
        user.setDeletedAt(new Date());
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        User user = findById(id);
        user.setDeletedAt(new Date());
        userRepository.save(user);
    }

    @Override
    public User findByLogin(String login) {
        Session session = hibernateFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        Predicate predicate1 = criteriaBuilder.isNull(root.get("deletedAt"));
        Predicate predicate2 = criteriaBuilder.equal(root.get("login"), login);
        Predicate andPredicate = criteriaBuilder.and(predicate1, predicate2);

        criteriaQuery.where(andPredicate);
        User user;
        try {
            user = session.createQuery(criteriaQuery).getSingleResult();
        } catch (Exception e) {
            user = null;
        }
        session.close();
        return user;
    }

    @Override
    public Set getAuthority(User user) {
        Set authorities = new HashSet<>();
        for (Role role : user.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        /*user.getRoles().forEach((r) -> {authorities.add(new SimpleGrantedAuthority(r.getName()));});*//*TODO*/
        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), getAuthority(user));
    }

}
