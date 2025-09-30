import { Router } from 'express';
import auth from './routes/userRoute';
import user from './routes/userRoute';
import role from './routes/roleRoute';
import allergies from './routes/allergiesRoute';
import medicalCondition from './routes/medicalConditionRoute';
import appointments from './routes/appointmentsRoute';
import specialization from './routes/specializationRoute';
import roomType from "./routes/roomTypeRoute";
import operationRoom from "./routes/operationRoomRoute";

export default () => {
	const app = Router();

	auth(app);
	user(app);
	role(app);
	appointments(app);
	allergies(app);
	medicalCondition(app);
  specialization(app);
  roomType(app);
  operationRoom(app);

	return app
}
