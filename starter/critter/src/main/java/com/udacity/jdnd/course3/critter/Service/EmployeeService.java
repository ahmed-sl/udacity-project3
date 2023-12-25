package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.DTO.EmployeeDTO;
import com.udacity.jdnd.course3.critter.DTO.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.Mapper.EmployeeMapper;
import com.udacity.jdnd.course3.critter.Model.Employee;
import com.udacity.jdnd.course3.critter.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.save(employeeMapper.mapToEmployee(employeeDTO));
        return employeeMapper.mapToDTO(employee);
    }

    public EmployeeDTO getEmployeeById(Long employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        return employeeMapper.mapToDTO(employee);
    }

    public void setEmployeeAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee != null){
            employee.setDaysAvailable(daysAvailable);
            employeeRepository.save(employee);
        }
    }
    public List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO request) {
        List<Employee> employees = employeeRepository.findByDaysAvailableAndSkillsIn(request.getDate().getDayOfWeek(),request.getSkills());
        return employeeMapper.mapToDTO(employees);
    }
}
