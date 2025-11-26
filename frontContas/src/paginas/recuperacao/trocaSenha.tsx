import { useEffect, useState } from "react";
import { Link, Navigate, useLocation, useNavigate } from "react-router-dom";
import { TrocasenhaNovo, type TrocasenhaRequest } from "../../services/authService";

function Trocasenha() {
    const navigate = useNavigate();
    const location = useLocation(); 

    const { email, codigo } = (location.state as { email: string, codigo: string }) || {};

    if (!email || !codigo) {
        return <Navigate to="/auth/recuperacao" replace />; 
    }

    const [novaSenha, setNovaSenha] = useState("");
    const [confirmarSenha, setConfirmarSenha] = useState("");
    const [senhaError, setSenhaError] = useState("");
    const [isLoading, setIsLoading] = useState(false);


    const validatePasswords = () => {
        if (confirmarSenha && novaSenha !== confirmarSenha) {
            setSenhaError("As senhas não coincidem");
        } else {
            setSenhaError("");
        }
    };

    useEffect(() => {
        validatePasswords();
    }, [novaSenha, confirmarSenha]);


    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault(); 
        setIsLoading(true);

        if (novaSenha.length < 3 || novaSenha !== confirmarSenha) {
            setSenhaError("As senhas não coincidem ou são muito curtas.");
            setIsLoading(false);
            return;
        }

        const finalDto: TrocasenhaRequest = {
            email: email, 
            token: codigo, 
            senha: novaSenha 
        };

        try {
            await TrocasenhaNovo(finalDto);
            
            window.alert("Senha alterada com êxito. Faça login com a nova senha.");
            navigate("/auth/login");

        } catch (error: any) {
            console.error("Erro ao trocar senha:", error.response || error.message);
            window.alert("Erro ao trocar senha. Seu código é inválido.");

        } finally {
            setIsLoading(false);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <p className="text-muted text-center">Crie sua nova senha para {email}</p>

            <div className="mb-3">
                <label className="form-label">Nova Senha</label>
                <input
                    type="password"
                    className="form-control"
                    value={novaSenha}
                    onChange={(e) => setNovaSenha(e.target.value)} 
                    placeholder="Digite sua nova senha"
                    minLength={3}
                    required/>
            </div>

            <div className="mb-3">
                <label className="form-label">Confirmar Senha</label>
                <input
                    type="password"
                    className="form-control"
                    value={confirmarSenha}
                    onChange={(e) => setConfirmarSenha(e.target.value)}
                    placeholder="Confirme sua senha"
                    minLength={3}
                    required
                    />
                {senhaError && <p style={{ color: "red" }}>{senhaError}</p>}
            </div>

            <button type="submit" className="btn btn-success w-100" disabled={isLoading || senhaError !== "" || novaSenha.length < 3}>
                {isLoading ? 'Trocando Senha...' : 'Alterar Senha'}
            </button>

            <p className="text-center mt-3">
                Voltar para a tela de <Link to="/auth/login">login</Link>
            </p>
        </form>
    );
}

export default Trocasenha;