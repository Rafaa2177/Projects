import { Request, Response, NextFunction } from 'express';
import { Inject, Service } from 'typedi';
import config from "../../config";

import IMedicalConditionController from "./IControllers/IMedicalConditionController";
import IMedicalConditionService from '../services/IServices/IMedicalConditionService';
import IMedicalConditionDTO from '../dto/IMedicalConditionDto';
import { MedicalConditionId } from "../domain/medicalConditionId";

import { Result } from "../core/logic/Result";

/**
 * Controller responsável pelas operações relacionadas às condições médicas.
 * Implementa a interface IMedicalConditionController.
 */
@Service() // O decorador Service indica que a classe será gerida pelo container de injeção de dependência do typedi.
export default class MedicalConditionController implements IMedicalConditionController {

  /**
   * Construtor da classe.
   * @param medicalConditionServiceInstance Serviço responsável pela lógica de negócios das condições médicas.
   */
  constructor(
    @Inject(config.services.medicalCondition.name) private medicalConditionServiceInstance: IMedicalConditionService
  ) {}

  /**
   * Cria uma nova condição médica.
   *
   * @param req - Objeto da requisição, contendo os dados da condição médica no corpo da requisição.
   * @param res - Objeto da resposta, utilizado para retornar a resposta ao cliente.
   * @param next - Função de passagem para o próximo middleware em caso de erro.
   *
   * @returns - Retorna a condição médica criada com status 201, ou um erro caso a criação falhe.
   */
  public async createMedicalCondition(req: Request, res: Response, next: NextFunction) {
    try {
      const { code, name } = req.body;

      // Chama o serviço para criar a condição médica
      const roleOrError = await this.medicalConditionServiceInstance.createMedicalCondition({ code, name } as IMedicalConditionDTO);

      // Se falhar, retorna erro 402
      if (roleOrError.isFailure) {
        return res.status(402).send();
      }

      // Retorna a condição criada com status 201
      const roleDTO = roleOrError.getValue();
      return res.status(201).json(roleDTO);
    }
    catch (e) {
      return next(e); // Em caso de erro, passa para o middleware de erro
    }
  };

  /**
   * Atualiza uma condição médica existente.
   *
   * @param req - Objeto da requisição, contendo o ID da condição médica nos parâmetros e os dados no corpo da requisição.
   * @param res - Objeto da resposta, utilizado para retornar a resposta ao cliente.
   * @param next - Função de passagem para o próximo middleware em caso de erro.
   *
   * @returns - Retorna a condição médica atualizada com status 200, ou um erro caso a atualização falhe.
   */
  public async updateMedicalCondition(req: Request, res: Response, next: NextFunction) {
    try {
      const { id } = req.params;
      const { name, code } = req.body;

      const medicalConditionData = { id, name, code };

      console.log('Update Request - ID:', id);
      console.log('Update Request - Body:', req.body);
      console.log('Update Request - Combined Data:', medicalConditionData);

      // Chama o serviço para atualizar a condição médica
      const medicalConditionOrError = await this.medicalConditionServiceInstance.updateMedicalCondition(medicalConditionData as IMedicalConditionDTO) as Result<IMedicalConditionDTO>;

      // Se falhar, retorna erro 404
      if (medicalConditionOrError.isFailure) {
        console.log('Update failed:', medicalConditionOrError.error);
        return res.status(404).send();
      }

      // Retorna a condição atualizada com status 200
      const medicalConditionDto = medicalConditionOrError.getValue();
      console.log('Update successful - Response:', medicalConditionDto);
      return res.status(200).json(medicalConditionDto);
    } catch (e) {
      console.error('Update error:', e);
      return next(e); // Em caso de erro, passa para o middleware de erro
    }
  };

  /**
   * Obtém todas as condições médicas.
   *
   * @param req - Objeto da requisição, não utilizado diretamente neste método.
   * @param res - Objeto da resposta, utilizado para retornar a resposta ao cliente.
   * @param next - Função de passagem para o próximo middleware em caso de erro.
   *
   * @returns - Retorna uma lista de todas as condições médicas com status 200, ou um erro caso não encontre condições.
   */
  public async getAllMedicalConditions(req: Request, res: Response, next: NextFunction) {
    try {
      // Chama o serviço para obter todas as condições médicas
      const result = await this.medicalConditionServiceInstance.getAllMedicalConditions();

      // Se falhar, retorna erro 404
      if (result.isFailure) {
        return res.status(404).json({ message: result.errorValue() });
      }

      // Retorna as condições médicas com status 200
      return res.status(200).json(result.getValue());
    } catch (e) {
      return next(e); // Em caso de erro, passa para o middleware de erro
    }
  }

  /**
   * Obtém uma condição médica específica pelo seu ID.
   *
   * @param req - Objeto da requisição, contendo o ID da condição médica nos parâmetros da URL.
   * @param res - Objeto da resposta, utilizado para retornar a resposta ao cliente.
   * @param next - Função de passagem para o próximo middleware em caso de erro.
   *
   * @returns - Retorna a condição médica encontrada com status 200, ou um erro caso não encontre a condição médica.
   */
  public async getMedicalConditionById(req: Request, res: Response, next: NextFunction) {
    try {
      // Extrai e converte o ID para o tipo adequado
      const id: MedicalConditionId = req.params.id as unknown as MedicalConditionId;

      // Chama o serviço para obter a condição médica pelo ID
      const result = await this.medicalConditionServiceInstance.getMedicalConditionById(id);

      // Se falhar, retorna erro 404
      if (result.isFailure) {
        return res.status(404).send(result.error);
      }

      // Retorna a condição médica encontrada com status 200
      return res.json(result.getValue());
    } catch (e) {
      return next(e); // Em caso de erro, passa para o middleware de erro
    }
  }
}
