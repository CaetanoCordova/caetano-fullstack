function LayoutLogin() {
  return (
    <form>
      <div className="mb-3">
        <label className="form-label">E-mail</label>
        <input type="email" className="form-control" placeholder="Digite seu e-mail" />
      </div>

      <div className="mb-3">
        <label className="form-label">Senha</label>
        <input type="password" className="form-control" placeholder="Digite sua senha" />
      </div>

      <button type="submit" className="btn btn-primary w-100">Entrar</button>

      <p className="text-center mt-3">
        Não tem conta? <a href="/register">Cadastre-se</a>
      </p>
    </form>
  );
}

export default LayoutLogin;