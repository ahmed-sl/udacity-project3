package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.DTO.ScheduleDTO;
import com.udacity.jdnd.course3.critter.Mapper.ScheduleMapper;
import com.udacity.jdnd.course3.critter.Model.Employee;
import com.udacity.jdnd.course3.critter.Model.Pet;
import com.udacity.jdnd.course3.critter.Model.Schedule;
import com.udacity.jdnd.course3.critter.Repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.Repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.Repository.PetRepository;
import com.udacity.jdnd.course3.critter.Repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private final PetRepository petRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;


    public ScheduleDTO saveSchedule(ScheduleDTO scheduleDTO){
        return scheduleMapper.mapToDTO(scheduleRepository.save(mapToSchedule(scheduleDTO)));
    }

    public List<ScheduleDTO> getScheduleForPet(Long petId){
        List<Schedule> schedules = scheduleRepository.getAllByPetsContains(petRepository.findById(petId).get());
        return scheduleMapper.mapToDTO(schedules);
    }

    public List<ScheduleDTO> getScheduleForEmployee(Long employeeId){
        List<Schedule> schedules = scheduleRepository.getAllByEmployeesContains(employeeRepository.findById(employeeId).get());
        return scheduleMapper.mapToDTO(schedules);
    }

    public List<ScheduleDTO> getScheduleForCustomer(Long customerId){
        List<Schedule> schedules = scheduleRepository.getAllByPetsIn(customerRepository.findById(customerId).get().getPets());
        return scheduleMapper.mapToDTO(schedules);
    }

    public List<ScheduleDTO> getAllSchedule() {
        return scheduleMapper.mapToDTO(scheduleRepository.findAll());
    }


    private Schedule mapToSchedule(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        schedule.setId(scheduleDTO.getId());
        schedule.setDate(scheduleDTO.getDate());

        List<Pet> pets = new ArrayList<>();
        for (Long petId:scheduleDTO.getPetIds()) {
            Pet pet = petRepository.findById(petId).orElse(null);
            if (pet != null) {
                pets.add(pet);
            }
        }
        schedule.setPets(pets);

        List<Employee> employees = new ArrayList<>();
        for (Long employeeId:scheduleDTO.getEmployeeIds()) {
            Employee employee = employeeRepository.findById(employeeId).orElse(null);
            if (employee != null) {
                employees.add(employee);
            }
        }
        schedule.setEmployees(employees);

        schedule.setActivities(scheduleDTO.getActivities());
        return schedule;
    }
}
