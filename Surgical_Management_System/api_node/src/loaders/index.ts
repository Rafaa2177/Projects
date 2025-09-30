import expressLoader from './express';
import dependencyInjectorLoader from './dependencyInjector';
import mongooseLoader from './mongoose';
import Logger from './logger';

import config from '../../config';

export default async ({ expressApp }) => {
  const mongoConnection = await mongooseLoader();
  Logger.info('✌️ DB loaded and connected!');

  const userSchema = {
    name: 'userSchema',
    schema: '../persistence/schemas/userSchema',
  };

  const roleSchema = {
    name: 'roleSchema',
    schema: '../persistence/schemas/roleSchema',
  };

  const medicalConditionSchema = {
    name: 'medicalConditionSchema',
    schema: '../persistence/schemas/medicalConditionSchema',
  };

  const appointmentSchema = {
    name: 'appointmentSchema',
    schema: '../persistence/schemas/appointmentSchema',
  };

  const roomTypeSchema = {
    name: 'roomTypeSchema',
    schema: '../persistence/schemas/roomTypeSchema',
  };

  const operationRoomSchema = {
    name: 'operationRoomSchema',
    schema: '../persistence/schemas/operationRoomSchema',
  };

  const roomTypeController = {
    name: config.controllers.roomType.name,
    path: config.controllers.roomType.path,
  };

  const operationRoomController = {
    name: config.controllers.operationRoom.name,
    path: config.controllers.operationRoom.path,
  };

  const roomTypeRepo = {
    name: config.repos.roomType.name,
    path: config.repos.roomType.path,
  };

  const operationRoomRepo = {
    name: config.repos.operationRoom.name,
    path: config.repos.operationRoom.path,
  };

  const roomTypeService = {
    name: config.services.roomType.name,
    path: config.services.roomType.path,
  };

  const operationRoomService = {
    name: config.services.operationRoom.name,
    path: config.services.operationRoom.path,
  };

  const appointmentController = {
    name: config.controllers.appointment.name,
    path: config.controllers.appointment.path,
  };

  const specializationSchema = {
    name: 'specializationSchema',
    schema: '../persistence/schemas/specializationSchema',
  };

  const roleController = {
    name: config.controllers.role.name,
    path: config.controllers.role.path,
  };

  const medicalConditionController = {
    name: config.controllers.medicalCondition.name,
    path: config.controllers.medicalCondition.path,
  };

  const appointmentRepo = {
    name: config.repos.appointment.name,
    path: config.repos.appointment.path,
  };

  const specializationController = {
    name: config.controllers.specialization.name,
    path: config.controllers.specialization.path,
  };

  const roleRepo = {
    name: config.repos.role.name,
    path: config.repos.role.path,
  };

  const userRepo = {
    name: config.repos.user.name,
    path: config.repos.user.path,
  };

  const medicalConditionRepo = {
    name: config.repos.medicalCondition.name,
    path: config.repos.medicalCondition.path,
  };

  const specializationRepo = {
    name: config.repos.specialization.name,
    path: config.repos.specialization.path,
  };

  const appointmentService = {
    name: config.services.appointment.name,
    path: config.services.appointment.path,
  };

  const roleService = {
    name: config.services.role.name,
    path: config.services.role.path,
  };

  const medicalConditionService = {
    name: config.services.medicalCondition.name,
    path: config.services.medicalCondition.path,
  };

  const specializationService = {
    name: config.services.specialization.name,
    path: config.services.specialization.path,
  };

  const allergiesSchema = {
    name: 'allergiesSchema',
    schema: '../persistence/schemas/allergiesSchema',
  };

  const allergiesController = {
    name: config.controllers.allergies.name,
    path: config.controllers.allergies.path,
  };

  const allergiesRepo = {
    name: config.repos.allergies.name,
    path: config.repos.allergies.path,
  };

  const allergiesService = {
    name: config.services.allergies.name,
    path: config.services.allergies.path,
  };

  await dependencyInjectorLoader({
    mongoConnection,
    schemas: [
      userSchema,
      roleSchema,
      allergiesSchema,
      medicalConditionSchema,
      appointmentSchema,
      specializationSchema,
      roomTypeSchema,
      operationRoomSchema,
    ],
    controllers: [
      roleController,
      allergiesController,
      medicalConditionController,
      appointmentController,
      specializationController,
      roomTypeController,
      operationRoomController,
    ],
    repos: [
      roleRepo,
      userRepo,
      allergiesRepo,
      medicalConditionRepo,
      appointmentRepo,
      specializationRepo,
      roomTypeRepo,
      operationRoomRepo,
    ],
    services: [
      roleService,
      allergiesService,
      medicalConditionService,
      appointmentService,
      specializationService,
      roomTypeService,
      operationRoomService,
    ],
  });
  Logger.info('✌️ Schemas, Controllers, Repositories, Services, etc. loaded');

  expressLoader({ app: expressApp });
  Logger.info('✌️ Express loaded');
};
