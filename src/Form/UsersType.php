<?php

namespace App\Form;
use Captcha\Bundle\CaptchaBundle\Form\Type\CaptchaType;

use App\Entity\Users;
use Captcha\Bundle\CaptchaBundle\Validator\Constraints\ValidCaptcha;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;


class UsersType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('username')
            ->add('password',PasswordType::class)
            ->add('email')
            ->add ("captchaCode",CaptchaType::class,[
                'captchaConfig' => 'ExampleCaptcha',
                'constraints' => [
                    new ValidCaptcha([
                        'message'=> 'Invalid captcha , please try again '
                    ])
                ]

            ])
        ;

    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Users::class,
        ]);
    }
}
