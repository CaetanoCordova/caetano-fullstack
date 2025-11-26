import { useSelector } from 'react-redux';
import type { RootState } from '../../store/store';

export const useAuth = () => {
    const role = useSelector((state: RootState) => state.auth.usuario?.role); 
    const isAuthenticated = useSelector((state: RootState) => state.auth.isAuthenticated);

    const hasRole = (requiredRole: string) => {
        return role === requiredRole;
    }

    return { isAuthenticated, role, hasRole };
};