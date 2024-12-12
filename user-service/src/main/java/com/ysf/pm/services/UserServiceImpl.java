package com.ysf.pm.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ysf.pm.dtos.UserDto;
import com.ysf.pm.exception.UserNotFoundException;
import com.ysf.pm.mappers.UserMapper;
import com.ysf.pm.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final UserMapper userMapper;
	private final UserRepository userRepository;
	
	static final String USER_NOT_FOUND="user not found";
	@Override
	public UserDto findByid(String userId) throws UserNotFoundException {
		return userMapper.toDto( userRepository.findById(userId).orElseThrow(()->
				new UserNotFoundException(USER_NOT_FOUND)
				));
	}

	@Override
	public void delete(String userId) throws UserNotFoundException {
		if(findByid(userId)!=null) {
			userRepository.deleteById(userId);
		}
		else throw new UserNotFoundException(USER_NOT_FOUND);
		
		
		
	}
	@Override
	public List<UserDto> findAllByIds(Iterable<String> ids){
		return userRepository.findAllById(ids).stream().map(userMapper::toDto).collect(Collectors.toList());
		
	}

	@Override
	public List<UserDto> findAll() {
		return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public UserDto save(UserDto userDto) {
		return userMapper.toDto( userRepository.save(userMapper.toEntity(userDto)));
	}

	@Override
	public UserDto update(UserDto userDto) throws UserNotFoundException {
		if(findByid(userDto.getId())!=null) {
			return userMapper.toDto( userRepository.save(userMapper.toEntity(userDto)));
		}
		else throw new UserNotFoundException(USER_NOT_FOUND);
	}

}
