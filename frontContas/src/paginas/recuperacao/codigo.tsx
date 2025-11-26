import { useState } from "react";
import { Link, Navigate, useLocation, useNavigate } from "react-router-dom";

interface CodigoInput {
    codigo: string; 
}

function Codigo() {
    const navigator = useNavigate();
    const location = useLocation(); 
    const emailDaRota = (location.state as { email: string })?.email;
    const [formData, setFormData] = useState<CodigoInput>({
        codigo: '',
    });

    if (!emailDaRota) {
      console.log("sem email");
        return <Navigate to="/auth/recuperacao" replace />; 
    }

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { value } = event.target;
        setFormData({ codigo: value }); 
    }

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
      console.log(emailDaRota);
        event.preventDefault();
        try {
             navigator("/auth/trocasenha", { 
                 state: { 
                     email: emailDaRota, 
                     codigo: formData.codigo 
                 } 
             });
        }
        catch (error) {
          console.error("Erro ao enviar email:", error);
        }
    }

    return (
        <form onSubmit={handleSubmit}>
            <p className="text-muted text-center">Insira o código que enviamos para seu e-mail</p>

            <div className="mb-3">
                <label className="form-label">Código...</label>
                <input
                    type="text"
                    name="codigo"
                    id="codigo-input"
                    className="form-control"
                    value={formData.codigo} 
                    onChange={handleChange}
                    placeholder="...de recuperação. Simples." />
            </div>
            <button type="submit" className="btn btn-primary w-100" disabled={formData.codigo.length === 0}>Enviar</button>
            <p className="text-center mt-3">
                Voltar para a tela de <Link to="/auth/login">login</Link>
            </p>
        </form>
    );
}

export default Codigo;