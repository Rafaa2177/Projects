import React from 'react';
import '@testing-library/jest-dom';
import { render, screen, fireEvent } from '@testing-library/react';
import StaffForm from "../../../src/Components/Staff/StaffForm";

describe('StaffForm Component', () => {
    const mockOnSubmit = jest.fn();

    beforeEach(() => {
        jest.clearAllMocks();
    });

    test('renders all fields for adding staff', () => {
        render(<StaffForm onSubmit={mockOnSubmit} />);

        // Check input fields
        expect(screen.getByPlaceholderText('First Name')).toBeInTheDocument();
        expect(screen.getByPlaceholderText('Last Name')).toBeInTheDocument();
        expect(screen.getByPlaceholderText('Email')).toBeInTheDocument();
        expect(screen.getByPlaceholderText('Phone Number')).toBeInTheDocument();
        expect(screen.getByText('Add Time Slot')).toBeInTheDocument();
    });

    test('handles input changes correctly', () => {
        render(<StaffForm onSubmit={mockOnSubmit} />);

        const firstNameInput = screen.getByPlaceholderText('First Name');
        fireEvent.change(firstNameInput, { target: { value: 'John' } });

        expect(firstNameInput.value).toBe('John');
    });

    test('allows adding and removing time slots', () => {
        render(<StaffForm onSubmit={mockOnSubmit} />);

        const addButton = screen.getByText('Add Time Slot');
        fireEvent.click(addButton);
        
        const removeButton = screen.getByText('✕');
        fireEvent.click(removeButton);

        expect(screen.queryByPlaceholderText(/Date|Time/)).not.toBeInTheDocument();
    });
    
    test('submits the form with valid data', () => {
        render(<StaffForm onSubmit={mockOnSubmit} />);

        fireEvent.change(screen.getByPlaceholderText('First Name'), { target: { value: 'John' } });
        fireEvent.change(screen.getByPlaceholderText('Last Name'), { target: { value: 'Doe' } });
        fireEvent.change(screen.getByPlaceholderText('Email'), { target: { value: 'john@example.com' } });
        fireEvent.change(screen.getByPlaceholderText('Phone Number'), { target: { value: '123456789' } });

        fireEvent.click(screen.getByText('Add Staff'));

        expect(mockOnSubmit).toHaveBeenCalledWith(
            expect.objectContaining({
                firstName: 'John',
                lastName: 'Doe',
                email: 'john@example.com',
                phoneNumber: '123456789'
            })
        );
    });
});
