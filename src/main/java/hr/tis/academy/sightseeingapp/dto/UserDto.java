package hr.tis.academy.sightseeingapp.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public class UserDto {
        @NotNull
        private String name;

        @NotNull
        @Email
        @Column(unique = true)
        private String email;

        private String phoneNumber;

        private LocalDate dateOfBirth;

        private AddressDto addressDto;

        public UserDto(String name, String email, String phoneNumber, LocalDate dateOfBirth, AddressDto addressDto) {
            this.name = name;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.dateOfBirth = dateOfBirth;
            this.addressDto = addressDto;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPhoneNumber() {
                return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
        }

        public LocalDate getDateOfBirth() {
                return dateOfBirth;
        }

        public void setDateOfBirth(LocalDate dateOfBirth) {
                this.dateOfBirth = dateOfBirth;
        }

        public AddressDto getAddressDto() {
                return addressDto;
        }

        public void setAddressDto(AddressDto addressDto) {
                this.addressDto = addressDto;
        }
}
