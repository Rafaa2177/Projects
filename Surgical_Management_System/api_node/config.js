const dotenv = require('dotenv');

// Define NODE_ENV como 'development' por padrão
process.env.NODE_ENV = process.env.NODE_ENV || 'development';

// Carrega o arquivo .env
const envFound = dotenv.config();
if (!envFound) {
  throw new Error("⚠️  Couldn't find .env file  ⚠️");
}

// Exporta o objeto de configuração
module.exports = {
  /**
   * Porta da aplicação
   */
  port: parseInt(process.env.PORT, 10) || 4000,

  /**
   * URL do banco de dados
   */
  databaseURL: process.env.MONGODB_URI || 'mongodb+srv://rootUser:12345678_.@sem5pi.da2l4.mongodb.net/?retryWrites=true&w=majority&appName=sem5pi',

  /**
   * Segredo do JWT
   */
  jwtSecret: process.env.JWT_SECRET || "my sakdfho2390asjod$%jl)!sdjas0i secret",

  /**
   * Configuração de logs (usado pelo Winston)
   */
  logs: {
    level: process.env.LOG_LEVEL || 'info',
  },

  /**
   * Configuração da API
   */
  api: {
    prefix: '/api',
  },

  /**
   * Configuração dos controllers
   */
  controllers: {
    role: {
      name: "RoleController",
      path: "../controllers/roleController",
    },
    roomType: {
      name: "RoomTypeController",
      path: "../controllers/roomTypeController",
    },
    medicalCondition: {
      name: "MedicalConditionController",
      path: "../controllers/medicalConditionController",
    },
    allergies: {
      name: "AllergiesController",
      path: "../controllers/allergiesController",
    },
    appointment: {
      name: "AppointmentController",
      path: "../controllers/appointmentController",
    },
    specialization: {
      name: "SpecializationController",
      path: "../controllers/specializationController",
    },
    operationRoom: {
      name: "OperationRoomController",
      path: "../controllers/operationRoomController",
    },
  },

  /**
   * Configuração dos repositórios
   */
  repos: {
    role: {
      name: "RoleRepo",
      path: "../repos/roleRepo",
    },
    roomType: {
      name: "RoomTypeRepo",
      path: "../repos/roomTypeRepo",
    },
    user: {
      name: "UserRepo",
      path: "../repos/userRepo",
    },
    allergies: {
      name: "AllergiesRepo",
      path: "../repos/allergiesRepo",
    },
    specialization: {
      name: "SpecializationRepo",
      path: "../repos/specializationRepo",
    },
    medicalCondition: {
      name: "MedicalConditionRepo",
      path: "../repos/medicalConditionRepo",
    },
    appointment: {
      name: "AppointmentRepo",
      path: "../repos/appointmentRepo",
    },
    operationRoom: {
      name: "OperationRoomRepo",
      path: "../repos/operationRoomRepo",
    },
  },

  /**
   * Configuração dos serviços
   */
  services: {
    role: {
      name: "RoleService",
      path: "../services/roleService",
    },
    roomType: {
      name: "RoomTypeService",
      path: "../services/roomTypeService",
    },
    medicalCondition: {
      name: "MedicalConditionService",
      path: "../services/medicalConditionService",
    },
    allergies: {
      name: "AllergiesService",
      path: "../services/allergiesService",
    },
    appointment: {
      name: "AppointmentService",
      path: "../services/appointmentService",
    },
    specialization: {
      name: "SpecializationService",
      path: "../services/specializationService",
    },
    operationRoom: {
      name: "OperationRoomService",
      path: "../services/operationRoomService",
    },
  },
};
