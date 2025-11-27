import { useState } from "react";
import { useDispatch } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { loginSucesso } from "../../store/authSlice";
import { LoginNovo, type LoginRequest} from "../../services/authService"; 

function Login() {
    const navigator = useNavigate();
    const dispatch = useDispatch();

    const [formData, setFormData] = useState<LoginRequest>({
      email:'',
      senha:''
    });

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
      const {name,value} = event.target;
      setFormData(prevState => ({
        ...prevState,
        [name]:value,
      }))
    }

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        try {
            const loginResponse = await LoginNovo(formData);
            const token = loginResponse.token;
            const role = loginResponse.role;

            if (token != null) {
                dispatch(loginSucesso({
                    usuario:{email:formData.email, nome: "", role: ""},
                    token:token
                }));
            }
            
            if (role === 'ROLE_ADMIN') {
                navigator("/usuarios"); 
            } else if (role === 'ROLE_USER') {
                navigator("/contas"); 
            } else {
                navigator("/auth/login"); 
            }

        } catch (error){
            console.error("Erro no login:", error);
        }
    }

  return (
    <form onSubmit={handleSubmit}>
      <p className="text-muted text-center">Bem-vindo! Essa é a tela de login</p>

      <div className="mb-3">
        <label className="form-label">E-mail</label>
        <input
          type="text"
          name="email"
          id="senha"
          className="form-control"
          value={formData.email}
          onChange={handleChange}
          placeholder="Digite seu e-mail" />
      </div>

      <div className="mb-3">
        <label className="form-label">Senha</label>
        <input
          type="password"
          name="senha"
          id="senha"
          className="form-control"
          value={formData.senha}
          onChange={handleChange}
          placeholder="Digite sua senha" />
      </div>

      <button type="submit" className="btn btn-primary w-100">Entrar</button>

      <p className="text-center mt-3">
        Não tem conta? <Link to="/auth/cadastrese">Cadastre-se</Link>
      </p>

      <p className="text-center mt-3">
        Esqueceu sua senha? <Link to="/auth/recuperacao">Clique aqui</Link>
      </p>
    </form>
  );
}

export default Login;
