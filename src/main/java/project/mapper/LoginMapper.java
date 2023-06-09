package project.mapper;

import org.apache.ibatis.annotations.Mapper;

import project.dto.UserDto;

@Mapper
public interface LoginMapper {

	public UserDto login(UserDto userDto) throws Exception;
	public int registUser(UserDto userDto) throws Exception;
	public UserDto selectUserByUserId(String userId);
}
