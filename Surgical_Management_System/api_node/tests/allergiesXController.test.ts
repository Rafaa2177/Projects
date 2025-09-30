import 'reflect-metadata';
import * as sinon from 'sinon';
import { Response, Request, NextFunction } from 'express';
import { Container } from 'typedi';
import { Result } from '../src/core/logic/Result';
import AllergiesController from '../src/controllers/allergiesController';
import IAllergiesService from '../src/services/IServices/IAllergiesService';
import IAllergiesDTO from '../src/dto/IAllergiesDTO';

describe('AllergiesController', () => {
  const sandbox = sinon.createSandbox();

  beforeEach(() => {
    Container.reset();
    const allergiesSchemaInstance = require('../src/persistence/schemas/allergiesSchema').default;
    Container.set('allergiesSchema', allergiesSchemaInstance);

    const allergiesRepoClass = require('../src/repos/allergiesRepo').default;
    const allergiesRepoInstance = Container.get(allergiesRepoClass);
    Container.set('AllergiesRepo', allergiesRepoInstance);

    const allergiesServiceClass = require('../src/services/allergiesService').default;
    const allergiesServiceInstance = Container.get(allergiesServiceClass);
    Container.set('AllergiesService', allergiesServiceInstance);
  });

  afterEach(() => {
    sandbox.restore();
  });

  it('should create an allergy successfully', async () => {
    const body = { id: '123', name: 'allergies12', code: '12345' };
    const req: Partial<Request> = { body };
    const res: Partial<Response> = { json: sinon.spy(), status: sinon.stub().returnsThis() };
    const next: Partial<NextFunction> = () => {};

    const allergiesServiceInstance = Container.get('AllergiesService') as IAllergiesService;
    sinon.stub(allergiesServiceInstance, 'createAllergies').returns(Promise.resolve(Result.ok<IAllergiesDTO>({ id: '123', name: req.body.name, code: req.body.code })));

    const ctrl = new AllergiesController(allergiesServiceInstance);

    await ctrl.createAllergies(req as Request, res as Response, next as NextFunction);

    sinon.assert.calledOnce(res.status as sinon.SinonStub);
    sinon.assert.calledWith(res.status as sinon.SinonStub, 201);
    sinon.assert.calledOnce(res.json as sinon.SinonSpy);
    sinon.assert.calledWith(res.json as sinon.SinonSpy, { id: '123', name: req.body.name, code: req.body.code });
  });

  it('should return 402 if creation fails', async () => {
    const body = { id: '123', name: 'allergies12', code: '12345' };
    const req: Partial<Request> = { body };
    const res: Partial<Response> = { json: sinon.spy(), status: sinon.stub().returnsThis() };
    const next: Partial<NextFunction> = () => {};

    const allergiesServiceInstance = Container.get('AllergiesService') as IAllergiesService;
    sinon.stub(allergiesServiceInstance, 'createAllergies').returns(Promise.resolve(Result.fail<IAllergiesDTO>('Error')));

    const ctrl = new AllergiesController(allergiesServiceInstance);

    await ctrl.createAllergies(req as Request, res as Response, next as NextFunction);

    sinon.assert.calledOnce(res.status as sinon.SinonStub);
    sinon.assert.calledWith(res.status as sinon.SinonStub, 402);
  });

  it('should update an allergy successfully', async () => {
    const body = { id: '123', name: 'allergies12', code: '12345' };
    const params = { id: '123' };
    const req: Partial<Request> = { body, params };
    const res: Partial<Response> = { json: sinon.spy(), status: sinon.stub().returnsThis() };
    const next: Partial<NextFunction> = () => {};

    const allergiesServiceInstance = Container.get('AllergiesService') as IAllergiesService;
    sinon.stub(allergiesServiceInstance, 'updateAllergies').returns(Promise.resolve(Result.ok<IAllergiesDTO>({ id: req.body.id, name: req.body.name, code: req.body.code })));

    const ctrl = new AllergiesController(allergiesServiceInstance);

    await ctrl.updateAllergies(req as Request, res as Response, next as NextFunction);

    sinon.assert.calledOnce(res.status as sinon.SinonStub);
    sinon.assert.calledWith(res.status as sinon.SinonStub, 201);
    sinon.assert.calledOnce(res.json as sinon.SinonSpy);
    sinon.assert.calledWith(res.json as sinon.SinonSpy, { id: '123', name: req.body.name, code: req.body.code });
  });

  it('should return 404 if update fails', async () => {
    const body = { id: '123', name: 'allergies12', code: '12345' };
    const params = { id: '123' };
    const req: Partial<Request> = { body, params };
    const res: Partial<Response> = { json: sinon.spy(), status: sinon.stub().returnsThis() };
    const next: Partial<NextFunction> = () => {};

    const allergiesServiceInstance = Container.get('AllergiesService') as IAllergiesService;
    sinon.stub(allergiesServiceInstance, 'updateAllergies').returns(Promise.resolve(Result.fail<IAllergiesDTO>('Error')));

    const ctrl = new AllergiesController(allergiesServiceInstance);

    await ctrl.updateAllergies(req as Request, res as Response, next as NextFunction);

    sinon.assert.calledOnce(res.status as sinon.SinonStub);
    sinon.assert.calledWith(res.status as sinon.SinonStub, 404);
  });

  /*it('should retrieve all allergies successfully', async () => {
    const req: Partial<Request> = {};
    const res: Partial<Response> = { json: sinon.spy(), status: sinon.stub().returnsThis() };
    const next: Partial<NextFunction> = () => {};

    const allergiesServiceInstance = Container.get('AllergiesService') as IAllergiesService;
    sinon.stub(allergiesServiceInstance, 'getAllAllergies').returns(Promise.resolve(Result.ok<IAllergiesDTO[]>([{ id: '123', name: 'allergies12', code: '12345' }])));

    const ctrl = new AllergiesController(allergiesServiceInstance);

    await ctrl.getAllAllergies(req as Request, res as Response, next as NextFunction);
    sinon.assert.calledOnce(res.status as sinon.SinonStub);
    sinon.assert.calledWith(res.status as sinon.SinonStub, 200);
    sinon.assert.calledOnce(res.json as sinon.SinonSpy);
    sinon.assert.calledWith(res.json as sinon.SinonSpy, [{ id: '123', name: 'allergies12', code: '12345' }]);
  });

  it('should return 404 if no allergies found', async () => {
    const req: Partial<Request> = {};
    const res: Partial<Response> = { json: sinon.spy(), status: sinon.stub().returnsThis() };
    const next: Partial<NextFunction> = () => {};

    const allergiesServiceInstance = Container.get('AllergiesService') as IAllergiesService;
    sinon.stub(allergiesServiceInstance, 'getAllAllergies').returns(Promise.resolve(Result.fail<IAllergiesDTO[]>('Error')));

    const ctrl = new AllergiesController(allergiesServiceInstance);

    await ctrl.getAllAllergies(req as Request, res as Response, next as NextFunction);

    sinon.assert.calledOnce(res.status as sinon.SinonStub);
    sinon.assert.calledWith(res.status as sinon.SinonStub, 404);
    sinon.assert.calledOnce(res.json as sinon.SinonSpy);
    sinon.assert.calledWith(res.json as sinon.SinonSpy, { message: 'Error' });
  });*/
});
