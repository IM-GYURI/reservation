package com.zerobase.reservation.entity;

import com.zerobase.reservation.type.Role;
import com.zerobase.reservation.util.RoleConverter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Member 엔티티
 * : 아이디, 멤버 키, 이름, 이메일, 비밀번호, 전화번호, 역할(Manager/Customer)
 */
@Getter
@NoArgsConstructor
@Table(indexes = {@Index(columnList = "member_key")})
@Entity(name = "Member")
public class MemberEntity extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, name = "member_key")
    private String memberKey;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String phone;

    @Convert(converter = RoleConverter.class)
    @Column(nullable = false)
    private Role role;

    @Builder
    public MemberEntity(String memberKey, String name, String email,
                        String password, String phone, Role role) {
        this.memberKey = memberKey;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.getKey()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}