import React from 'react';
import { Route } from 'react-router-dom';
import ProtectedRoute from './ProtectedRoute';
import ListPacientPage from '../pages/Admin/ListPacientPage';
import AddPacientPage from '../Pages/AddPatientPage';
import UpdatePacientPage from '../Pages/UpdatePacientPage';
import DeletePacientPage from '../Pages/DeletePatientPage';
import AddStaffPage from "../pages/Admin/AddStaffPage";
import ListStaffPage from "../pages/Admin/ListStaffPage";
import CreateBackOfficeUserPage from "../pages/Admin/CreateBackOfficeUserPage";
import AdminDashboard from "../Components/Admin/AdminDahsboard";
import DeleteStaffPage from "../pages/Admin/DeleteStaffPage";
import LoginPage from "../app/LoginPage2";

const AdminRoutes = ({ user, logout }) => (
    <>
        <Route path="/admin/*" element={<ProtectedRoute user={user} role="Admins"><AdminDashboard user={user} logout={logout} /></ProtectedRoute>}>
            
            <Route path="pacientes/listar" element={<ListPacientPage />} />
            <Route path="pacientes/criar" element={<AddPacientPage />} />
            <Route path="pacientes/atualizar/:recordNumber" element={<UpdatePacientPage />} />
            <Route path="pacientes/apagar/:recordNumber" element={<DeletePacientPage />} />
            <Route path="staffs/add" element={<AddStaffPage />} />
            <Route path="staffs/list" element={<ListStaffPage />} />
            <Route path="staffs/inactivate" element={<InactivateStaffPage />} />
            <Route path="staffs/delete" element={<DeleteStaffPage />} />
            <Route path="user/create" element={<CreateBackOfficeUserPage />} />
            <Route path = "user/login" element = {<LoginPage />} />
        </Route>
    </>
);

export default AdminRoutes;
