import { Request, Response, NextFunction } from 'express';

/**
 * Interface para o controlador de condições médicas.
 * Define os métodos que um controlador de condições médicas deve implementar.
 */
export default interface IMedicalConditionController {

  /**
   * Cria uma nova condição médica.
   *
   * @param req - Objeto da requisição, que contém os dados da nova condição médica no corpo.
   * @param res - Objeto da resposta, utilizado para retornar a resposta ao cliente.
   * @param next - Função de passagem para o próximo middleware em caso de erro.
   *
   * @returns - O método não retorna um valor diretamente. Ele responde com o status e o corpo da resposta HTTP.
   */
  createMedicalCondition(req: Request, res: Response, next: NextFunction);

  /**
   * Atualiza uma condição médica existente.
   *
   * @param req - Objeto da requisição, que contém o ID da condição médica nos parâmetros e os dados atualizados no corpo.
   * @param res - Objeto da resposta, utilizado para retornar a resposta ao cliente.
   * @param next - Função de passagem para o próximo middleware em caso de erro.
   *
   * @returns - O método não retorna um valor diretamente. Ele responde com o status e o corpo da resposta HTTP.
   */
  updateMedicalCondition(req: Request, res: Response, next: NextFunction);

  /**
   * Obtém todas as condições médicas.
   *
   * @param req - Objeto da requisição, que pode conter parâmetros de consulta, mas não é utilizado diretamente aqui.
   * @param res - Objeto da resposta, utilizado para retornar a lista de condições médicas ao cliente.
   * @param next - Função de passagem para o próximo middleware em caso de erro.
   *
   * @returns - O método não retorna um valor diretamente. Ele responde com o status e o corpo da resposta HTTP.
   */
  getAllMedicalConditions(req: Request, res: Response, next: NextFunction);

  /**
   * Obtém uma condição médica específica pelo seu ID.
   *
   * @param req - Objeto da requisição, que contém o ID da condição médica nos parâmetros da URL.
   * @param res - Objeto da resposta, utilizado para retornar a condição médica encontrada ao cliente.
   * @param next - Função de passagem para o próximo middleware em caso de erro.
   *
   * @returns - O método não retorna um valor diretamente. Ele responde com o status e o corpo da resposta HTTP.
   */
  getMedicalConditionById(req: Request, res: Response, next: NextFunction);
}
