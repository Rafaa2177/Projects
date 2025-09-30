import React, { useState, useEffect } from 'react';
import './AppointmentForm.css';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';

interface Patient {
  id: string;
  firstName: string;
  lastName: string;
  fullName: string;
  dateOfBirth: string;
  dateOfBirthFormatted: string;
  email: string;
  phoneNumber: string;
  emergencyContact: string;
  gender: string;
  medicalConditions: string[];
  appointments: any[];
  recordNumber: {
    value: string;
  };
}

interface Doctor {
  id: string;
  firstName: string;
  lastName: string;
  fullName: string;
  email: string;
  phoneNumber: string;
  licenseNumber: {
    value: string;
  };
  role: number;
  specialization: string;
  timeSlots: any[];
}

interface FormData {
  pacientId: string;
  doctorId: string;
  operationType: string;
  startDate: string;
  endDate: string;
  room: string;
}

const AppointmentForm: React.FC = () => {
  const [patients, setPatients] = useState<Patient[]>([]);
  const [doctors, setDoctors] = useState<Doctor[]>([]);
  const [startDate, setStartDate] = useState<Date | null>(null);
  const [endDate, setEndDate] = useState<Date | null>(null);
  const [formData, setFormData] = useState<FormData>({
    pacientId: '',
    doctorId: '',
    operationType: 'Cardiology',
    startDate: '',
    endDate: '',
    room:'or1',
  });
  

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    // Implementar a lógica de submissão aqui
    
    if (!startDate || !endDate) {
      alert('Please select both start and end dates');
      return;
    }
    console.log('Form submitted:', formData);
    console.log('Start data: ',startDate.toISOString());
    const formattedData = {
        ...formData, 
      doctorId: localStorage.getItem("docId"),
      startDate: startDate.toISOString(),
      endDate: endDate.toISOString(), 
        status: "SCHEDULED",
    };
    console.log(formattedData);
    try {
      const response = await fetch('http://localhost:4000/api/appointments', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formattedData),
      });
      
  
      const result = await response.json();
      console.log('Appointment created:', result);
    } catch (error) {
      console.error('Error submitting appointment:', error);
    }
  };

  useEffect(() => {
    const fetchPatients = async (): Promise<void> => {
      try {
        const response = await fetch('https://localhost:5001/api/Patients');
        if (!response.ok) {
          throw new Error('Failed to fetch patients');
        }
        const data = await response.json();
        const patientDTOs: Patient[] = data.map((patient: any) => ({
          id: patient.id,
          firstName: patient.firstName,
          lastName: patient.lastName,
          fullName: patient.fullName,
          dateOfBirth: patient.dateOfBirth,
          dateOfBirthFormatted: patient.dateOfBirthFormatted,
          email: patient.email,
          phoneNumber: patient.phoneNumber,
          emergencyContact: patient.emergencyContact,
          gender: patient.gender,
          medicalConditions: patient.medicalConditions,
          appointments: patient.appointments,
          recordNumber: patient.recordNumber
        }));
        setPatients(patientDTOs);
      } catch (error) {
        console.error('Error fetching patients:', error);
      }
    };

    const fetchDoctors = async (): Promise<void> => {
      try {
        const response = await fetch('https://localhost:5001/api/Staffs/active');
        if (!response.ok) {
          throw new Error('Failed to fetch doctors');
        }
        const data = await response.json();
        const doctorDTOs: Doctor[] = data
          .filter((doctor: any) => doctor.role === 0)
          .map((doctor: any) => ({
            id: doctor.id,
            firstName: doctor.firstName,
            lastName: doctor.lastName,
            fullName: doctor.fullName,
            email: doctor.email,
            phoneNumber: doctor.phoneNumber,
            licenseNumber: { value: doctor.licenseNumber.value },
            role: doctor.role,
            specialization: doctor.specialization,
            timeSlots: doctor.timeSlots
          }));
        setDoctors(doctorDTOs);
      } catch (error) {
        console.error('Error fetching doctors:', error);
      }
    };

    fetchPatients();
    fetchDoctors();
  }, []);

  return (
    <form onSubmit={handleSubmit}
      className="flex flex-col gap-4 max-w-lg mx-auto p-6 bg-white dark:bg-gray-800 rounded-lg shadow-lg h-[42vh]">
      <select
        name="pacientId"
        value={formData.pacientId}
        onChange={handleChange}
        className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
        required
      >
        <option value="" disabled>Select Patient</option>
        {patients.map(patient => (
          <option key={patient.id} value={patient.recordNumber.value}>
            {patient.fullName}
          </option>
        ))}
      </select>
      <DatePicker
        selected={startDate}
        onChange={(startdate:Date) => setStartDate(startdate)}
        showTimeSelect
        timeFormat="HH:mm"
        timeIntervals={15}
        minTime={new Date(new Date().setHours(0, 0))}
        maxDate={endDate ?? undefined}
        maxTime={
          endDate && startDate && new Date(startDate).toDateString() === new Date(endDate).toDateString()
              ? new Date(endDate.getTime() - 15 * 60000) // Horário máximo é 15 minutos antes do endDate
              : new Date(new Date().setHours(23, 59)) // Horário máximo padrão
      }
        dateFormat="MMMM d, yyyy h:mm aa"
        placeholderText='Appointment Start Date'
        className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full " required/>
        <DatePicker
        selected={endDate}
        onChange={(endDate:Date) => setEndDate(endDate)}
        showTimeSelect
        timeFormat="HH:mm"
        timeIntervals={15}
        minDate={startDate ?? undefined}
        minTime={
          startDate && endDate && new Date(startDate).toDateString() === new Date(endDate).toDateString()
              ? new Date(new Date(startDate).getTime() + 15 * 60000) // Próximo intervalo de tempo
              : new Date(new Date().setHours(0, 0)) // Começa do início do dia para outros dias
      }
        maxTime={new Date(new Date().setHours(23, 59))}
        dateFormat="MMMM d, yyyy h:mm aa"
        placeholderText='Appointment End Date'
        
        className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full " required/>
        <select
                name="operationType"
                value={formData.operationType}
                onChange={handleChange}
                defaultValue={"Cardiology"}
                className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
                required
            >
                <option value="Cardiology">Cardiology</option>
                <option value="Neurology">Neurology</option>
                <option value="Orthopedics">Orthopedics</option>
                <option value="Gastroenterology">Gastroenterology</option>
                <option value="Pulmonology">Pulmonology</option>
                <option value="Oncology">Oncology</option>
                <option value="Endocrinology">Endocrinology</option>
                <option value="Pediatrics">Pediatrics</option>
                <option value="Dermatology">Dermatology</option>
                <option value="Urology">Urology</option>
                <option value="Gynecology/Obstetrics">Gynecology/Obstetrics</option>
                <option value="Ophthalmology">Ophthalmology</option>
                <option value="Psychiatry">Psychiatry</option>
                <option value="General Surgery">General Surgery</option>
                <option value="Radiology">Radiology</option>
                <option value="Emergency Medicine">Emergency Medicine</option>
            </select>
      <select
                name="room"
                value={formData.room}
                onChange={handleChange}
                defaultValue={"or1"}
                className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
                required
            >
                <option value="or1">Operation Room 1</option>
                <option value="or2">Operation Room 2</option>
                <option value="or3">Operation Room 3</option>
                <option value="or4">Operation Room 4</option>
            </select>
      <button
        type="submit"
        className="mt-auto bg-pink-400 text-white py-4 px-10 rounded-lg hover:bg-pink-300 transition-colors text-xl bottom-0 w-full"
      >
        Submit Operation Request
      </button>
    </form>
  );
};

export default AppointmentForm; 