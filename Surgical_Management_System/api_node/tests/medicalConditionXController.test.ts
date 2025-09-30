import 'reflect-metadata';
import * as sinon from 'sinon';
import { Response, Request, NextFunction } from 'express';
import { Container } from 'typedi';
import { Result } from '../src/core/logic/Result';
import MedicalConditionController from '../src/controllers/medicalConditionController';
import IMedicalConditionService from '../src/services/IServices/IMedicalConditionService';
import IMedicalConditionDTO from '../src/dto/IMedicalConditionDto';

describe('MedicalConditionController', () => {
  const sandbox = sinon.createSandbox();

  beforeEach(() => {
    Container.reset();
    const medicalConditionSchemaInstance = require('../src/persistence/schemas/medicalConditionSchema').default;
    Container.set('medicalConditionSchema', medicalConditionSchemaInstance);

    const medicalConditionRepoClass = require('../src/repos/medicalConditionRepo').default;
    const medicalConditionRepoInstance = Container.get(medicalConditionRepoClass);
    Container.set('MedicalConditionRepo', medicalConditionRepoInstance);

    const medicalConditionServiceClass = require('../src/services/medicalConditionService').default;
    const medicalConditionServiceInstance = Container.get(medicalConditionServiceClass);
    Container.set('MedicalConditionService', medicalConditionServiceInstance);
  });

  afterEach(() => {
    sandbox.restore();
  });

  it('should create a medical condition successfully', async () => {
    const body = { code: '12345', name: 'Asthma' };
    const req: Partial<Request> = { body };
    const res: Partial<Response> = { json: sinon.spy(), status: sinon.stub().returnsThis() };
    const next: Partial<NextFunction> = () => {};

    const medicalConditionServiceInstance = Container.get('MedicalConditionService') as IMedicalConditionService;
    sinon.stub(medicalConditionServiceInstance, 'createMedicalCondition').returns(Promise.resolve(Result.ok<IMedicalConditionDTO>({ code: '12345', name: 'Asthma' })));

    const ctrl = new MedicalConditionController(medicalConditionServiceInstance);

    const aaa = await ctrl.createMedicalCondition(req as Request, res as Response, next as NextFunction);
    sinon.assert.calledOnce(res.status as sinon.SinonStub);
    sinon.assert.calledWith(res.status as sinon.SinonStub, 201);
    sinon.assert.calledOnce(res.json as sinon.SinonSpy);
    sinon.assert.calledWith(res.json as sinon.SinonSpy, { code: '12345', name: 'Asthma' });
  });

  it('should return 402 if creation fails', async () => {
    const body = { code: '12345', name: 'Asthma' };
    const req: Partial<Request> = { body };
    const res: Partial<Response> = { json: sinon.spy(), status: sinon.stub().returnsThis() };
    const next: Partial<NextFunction> = () => {};

    const medicalConditionServiceInstance = Container.get('MedicalConditionService') as IMedicalConditionService;
    sinon.stub(medicalConditionServiceInstance, 'createMedicalCondition').returns(Promise.resolve(Result.fail<IMedicalConditionDTO>('Error')));

    const ctrl = new MedicalConditionController(medicalConditionServiceInstance);

    await ctrl.createMedicalCondition(req as Request, res as Response, next as NextFunction);

    sinon.assert.calledOnce(res.status as sinon.SinonStub);
    sinon.assert.calledWith(res.status as sinon.SinonStub, 402);
  });

  it('should update a medical condition successfully', async () => {
    const body = { code: '12345', name: 'Asthma' };
    const req: Partial<Request> = { body };
    const res: Partial<Response> = { json: sinon.spy(), status: sinon.stub().returnsThis() };
    const next: Partial<NextFunction> = () => {};

    const medicalConditionServiceInstance = Container.get('MedicalConditionService') as IMedicalConditionService;
    sinon.stub(medicalConditionServiceInstance, 'updateMedicalCondition').returns(Promise.resolve(Result.ok<IMedicalConditionDTO>({ code: '12345', name: 'Asthma' })));

    const ctrl = new MedicalConditionController(medicalConditionServiceInstance);

    await ctrl.updateMedicalCondition(req as Request, res as Response, next as NextFunction);

    sinon.assert.calledOnce(res.status as sinon.SinonStub);
    sinon.assert.calledWith(res.status as sinon.SinonStub, 201);
    sinon.assert.calledOnce(res.json as sinon.SinonSpy);
    sinon.assert.calledWith(res.json as sinon.SinonSpy, { code: '12345', name: 'Asthma' });
  });

  it('should return 404 if update fails', async () => {
    const body = { code: '12345', name: 'Asthma' };
    const req: Partial<Request> = { body };
    const res: Partial<Response> = { json: sinon.spy(), status: sinon.stub().returnsThis() };
    const next: Partial<NextFunction> = () => {};

    const medicalConditionServiceInstance = Container.get('MedicalConditionService') as IMedicalConditionService;
    sinon.stub(medicalConditionServiceInstance, 'updateMedicalCondition').returns(Promise.resolve(Result.fail<IMedicalConditionDTO>('Error')));

    const ctrl = new MedicalConditionController(medicalConditionServiceInstance);

    await ctrl.updateMedicalCondition(req as Request, res as Response, next as NextFunction);

    sinon.assert.calledOnce(res.status as sinon.SinonStub);
    sinon.assert.calledWith(res.status as sinon.SinonStub, 404);
  });

  it('should retrieve all medical conditions successfully', async () => {
    const req: Partial<Request> = {};
    const res: Partial<Response> = { json: sinon.spy(), status: sinon.stub().returnsThis() };
    const next: Partial<NextFunction> = () => {};

    const medicalConditionServiceInstance = Container.get('MedicalConditionService') as IMedicalConditionService;
    sinon.stub(medicalConditionServiceInstance, 'getAllMedicalConditions').returns(Promise.resolve(Result.ok<IMedicalConditionDTO[]>([{ code: '12345', name: 'Asthma' }])));

    const ctrl = new MedicalConditionController(medicalConditionServiceInstance);

    await ctrl.getAllMedicalConditions(req as Request, res as Response, next as NextFunction);

    sinon.assert.calledOnce(res.status as sinon.SinonStub);
    sinon.assert.calledWith(res.status as sinon.SinonStub, 200);
    sinon.assert.calledOnce(res.json as sinon.SinonSpy);
    sinon.assert.calledWith(res.json as sinon.SinonSpy, [{ code: '12345', name: 'Asthma' }]);
  });

  it('should return 404 if no medical conditions found', async () => {
    const req: Partial<Request> = {};
    const res: Partial<Response> = { json: sinon.spy(), status: sinon.stub().returnsThis() };
    const next: Partial<NextFunction> = () => {};

    const medicalConditionServiceInstance = Container.get('MedicalConditionService') as IMedicalConditionService;
    sinon.stub(medicalConditionServiceInstance, 'getAllMedicalConditions').returns(Promise.resolve(Result.fail<IMedicalConditionDTO[]>('Error')));

    const ctrl = new MedicalConditionController(medicalConditionServiceInstance);

    await ctrl.getAllMedicalConditions(req as Request, res as Response, next as NextFunction);

    sinon.assert.calledOnce(res.status as sinon.SinonStub);
    sinon.assert.calledWith(res.status as sinon.SinonStub, 404);
    sinon.assert.calledOnce(res.json as sinon.SinonSpy);
    sinon.assert.calledWith(res.json as sinon.SinonSpy, { message: 'Error' });
  });
});
