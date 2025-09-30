export enum OperationType{
    "Cardiology" = 0,
    "Neurology" = 1,
    "Orthopedics" = 2,
    "Gastroenterology" = 3,
    "Pulmonology" = 4,
    "Oncology" = 5,
    "Endocrinology" = 6,
    "Pediatrics" = 7,
    "Dermatology" = 8,
    "Urology" = 9,
    "Gynecology/Obstetrics" = 10,
    "Ophthalmology" = 11,
    "Psychiatry" = 12,
    "General Surgery" = 13,
    "Radiology" = 14,
    "Emergency Medicine" = 15,
  }
  export const isValidOperationTypeIndex = (choice: string): boolean => {
    // Verifica se a string é um dos valores do enum
    return Object.values(OperationType)
      .filter(value => typeof value === 'string')
      .includes(choice);
  }

// Opcional: função para obter todos os índices válidos
export const getValidOperationTypeIndices = (): number[] => {
    return Object.keys(OperationType)
        .filter(key => !isNaN(Number(key)))
        .map(key => Number(key));
}