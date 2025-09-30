import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import '@testing-library/jest-dom';
import EditOperationRequestModal from '../../../src/components/OperationRequests/EditOperationRequestModal';

const mockOperationRequest = {
    doctorId: 'doc123',
    patientId: 'pat456',
    operationType: 'Surgery',
    operationDate: '2023-12-01T00:00:00.000Z',
    priority: 'High',
};

describe('EditOperationRequestModal', () => {
    it('renders the modal with initial data', () => {
        render(
            <EditOperationRequestModal
                operationRequest={mockOperationRequest}
                isOpen={true}
                onClose={jest.fn()}
                onSave={jest.fn()}
            />
        );

        // Verifica se os campos aparecem com os valores iniciais
        expect(screen.getByLabelText(/Doctor ID/i)).toHaveValue(mockOperationRequest.doctorId);
        expect(screen.getByLabelText(/Patient ID/i)).toHaveValue(mockOperationRequest.patientId);
        expect(screen.getByLabelText(/Operation Type/i)).toHaveValue(mockOperationRequest.operationType);
        expect(screen.getByLabelText(/Operation Date/i)).toHaveValue('2023-12-01');
        expect(screen.getByLabelText(/Priority/i)).toHaveValue(mockOperationRequest.priority);
    });

    it('calls onClose when the cancel button is clicked', async () => {
        const onCloseMock = jest.fn();

        render(
            <EditOperationRequestModal
                operationRequest={mockOperationRequest}
                isOpen={true}
                onClose={onCloseMock}
                onSave={jest.fn()}
            />
        );

        const cancelButton = screen.getByRole('button', { name: /cancel/i });
        fireEvent.click(cancelButton);

        // Aguarda o tempo da animação
        await new Promise(r => setTimeout(r, 300));

        expect(onCloseMock).toHaveBeenCalledTimes(1);
    });

    test('updates form values when fields are edited', async () => {
        const mockData = {
            doctorId: 'doc123',
            patientId: 'pat456',
            operationType: 'CAAA',
            operationDate: '2023-12-01',
            priority: 'High',
        };

        render(
            <EditOperationRequestModal
                operationRequest={mockData}
                isOpen={true}
                onClose={jest.fn()}
                onSave={jest.fn()}
            />
        );

        // Encontre o campo Operation Type
        const operationTypeInput = screen.getByTestId('operation-type-input');
        expect(operationTypeInput).toBeInTheDocument();
    
      fireEvent.change(operationTypeInput,{target: {value: 'New Operation'}});
        // Valide o novo valor
        expect(operationTypeInput).toHaveValue('New Operation');
    });

    it('formats the date input correctly', () => {
        render(
            <EditOperationRequestModal
                operationRequest={mockOperationRequest}
                isOpen={true}
                onClose={jest.fn()}
                onSave={jest.fn()}
            />
        );

        // Verifica se a data inicial está no formato correto
        const dateInput = screen.getByLabelText(/Operation Date/i);
        expect(dateInput).toHaveValue('2023-12-01');
    });

    it('calls onSave with updated data when save button is clicked', () => {
        const onSaveMock = jest.fn();

        render(
            <EditOperationRequestModal
                operationRequest={mockOperationRequest}
                isOpen={true}
                onClose={jest.fn()}
                onSave={onSaveMock}
            />
        );

        // Atualiza os valores dos campos
        fireEvent.change(screen.getByLabelText(/Operation Type/i), {target: { value: 'Updated Surgery'} });
        fireEvent.change(screen.getByLabelText(/Priority/i), {target: { value: 'Medium'} });

        // Clica no botão de salvar
        fireEvent.click(screen.getByRole('button', { name: /save/i }));

        expect(onSaveMock).toHaveBeenCalledWith({
            doctorId: 'doc123',
            patientId: 'pat456',
            operationType: 'Updated Surgery',
            operationDate: '2023-12-01T00:00:00.000Z',
            priority: 'Medium',
        });
    });

    it('does not render the modal when isOpen is false', () => {
        render(
            <EditOperationRequestModal
                operationRequest={mockOperationRequest}
                isOpen={false}
                onClose={jest.fn()}
                onSave={jest.fn()}
            />
        );

        // Verifica que o modal não está visível
        expect(screen.queryByText(/Edit Operation Request/i)).not.toBeInTheDocument();
    });
});