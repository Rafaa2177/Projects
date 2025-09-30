import React from 'react';
import { Link, Outlet, useNavigate } from 'react-router-dom';

const AdminDashboard = ({ user, logout }) => {
    const navigate = useNavigate();

    const handleLogout = () => {
        logout();
        navigate('/');
    };

    return (
        <div className="admin-dashboard">
            <aside className="sidebar">
                <h2>Admin Menu</h2>
                {user && <p>Welcome, {user.name}</p>}
                <nav>
                    <ul>
                        <li><Link to="addpacientprofile">Add Pacient Profile</Link></li>
                        <li><Link to="updatepacientprofile">Update Pacient Profile</Link></li>
                        <li><Link to="deletepatientprofile">Delete Pacient Profile</Link></li>
                        <li><Link to="getpatientprofile">Get Pacient Profile</Link></li>
                        <li><Link to="getstaff">Get Staff</Link></li>
                        <li><Link to="deletestaff">Delete Staff</Link></li>
                        <li><Link to="addstaff">Add Staff</Link></li>
                        <li><Link to="updatestaff">Update Staff</Link></li>
                        <li><Link to="deactivatestaff">Deactivate Staff</Link></li>

                    </ul>
                </nav>
                <button onClick={handleLogout} className="logout-button">Logout</button>
            </aside>
            <main className="content">
                <Outlet />
            </main>
        </div>
    );
};

export default AdminDashboard;